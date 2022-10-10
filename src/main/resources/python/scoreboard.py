import requests
import pymysql
import schedule
import time

def spiderme():

    conn = pymysql.connect(  # 赋值给 conn连接对象
        host='42.192.36.136',  # 本地回环地址
        port=3555,  # 默认端口
        user='root',  # 用户名
        password='mysql114514',  # 密码
        database='gxucpc',  # 连接数据库名称
        charset='utf8'  # 编码 不能写utf-8
    )
    cursor = conn.cursor()
    getUrl = "select * from mapper_contest where is_spider=1 and fault_time<10"
    cursor.execute(getUrl)
    ls = cursor.fetchall()
    for ur in ls:
        url = ur[5]
        contestId = ur[1]
        try:
            sql = "insert into scoreboard (`contest_id`,`content`, `rank`,`team_id`,`num_solved`,`total_time`)values " \
                  "(%s,%s,%s," \
                  "%s,%s,%s) "
            delSql = "delete from scoreboard where contest_id=%s"
            r = requests.get(url)
            cursor.execute(delSql, contestId)
            dirt = r.json()
            ss = str(dirt['contest_time']).split(":")
            ss = [float(i) for i in ss]
            se_times = int(ss[0]*60*60 + ss[1] * 60 + ss[2])

            cursor.execute("update mapper_contest set contest_time=%s where id=%s",(se_times,ur[3]))
            for i in dirt['rows']:
                s = str(i['problems'])
                param = (contestId, s, i['rank'], i['team_id'], i['score']['num_solved'], i['score']['total_time'])
                cursor.execute(sql, param)
            conn.commit()
            # conn.close()
        except Exception as e:
            with open("111.txt","a",encoding="utf8")as f:
                f.write(e)
            conn.rollback()
            addFault = "update mapper_contest set fault_time=%s where id=%s"
            param = (ur[6] + 1, ur[3])
            cursor.execute(addFault, param)
            conn.commit()
    conn.close()

if __name__ == '__main__':
    spiderme()
    # schedule.every(4).seconds.do(spiderme)
    # while True:
    #     schedule.run_pending()
    #     time.sleep(1)
