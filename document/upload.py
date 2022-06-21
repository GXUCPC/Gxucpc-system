import pymongo
from gridfs import GridFS
from bson.objectid import ObjectId
# client = MongoClient('42.192.36.136', 2556)
import urllib.parse
import datetime
# username = urllib.parse.quote_plus('admin')
# password = urllib.parse.quote_plus('mongodb114514')
# client = MongoClient('mongodb://%s:%s@42.192.36.136:2556'%(username,password))

# # mongo_auth = client.admin
# # mongo_auth.authenticate('admin', 'mongodb114514')
# print(client.server_info())
#
# info = {
#     'name': "test",
#     'text': "hello world",
#     'date': datetime.datetime.now()
# }
# mongo_db = client['maomao']
# mongo_collection = mongo_db['maomao']
# mongo_collection.insert_one(info)

import pymongo
import os


class MongoGridFS(object):
    '''
    classdocs
    '''
    UploadCache = "uploadcache"
    dbURL = "mongodb://42.192.36.136:2556"

    def __init__(self, params):
        '''
        Constructor
        '''

    # 存入小型文件《4M
    def upLoadSmallFile(self, file_coll, file_name, data_link):
        # client = pymongo.MongoClient(self.dbURL)
        username = urllib.parse.quote_plus('admin')
        password = urllib.parse.quote_plus('mongodb114514')
        client = pymongo.MongoClient('mongodb://%s:%s@42.192.36.136:2556'%(username,password))
        print(client.server_info())
        db = client["store"]
        file_dict = {
            "filename": "test",
            "filesize": 100,
            "uploadDate": datetime.datetime.now()
        }

        collection = db[file_coll]
        fsize = os.path.getsize(file_name)
        file_dict["filesize"] = fsize
        with open(file_name, 'rb') as file_r:
            file_data = file_r.read()
            file_dict["file_data"] = file_data
            file_dict["filename"] = file_name
            file_ = collection.insert_one(file_dict)

        return file_

    def downLoadFile(self,file_coll,file_name,out_name,ver):
        """
         按文件名下载
         :param file_coll:集合名
         :param file_name:文件名
         :param out_name: 下载下来的文件名
         :param ver: 版本号，默认-1表示最近一次的记录
         :return:
        """
        username = urllib.parse.quote_plus('admin')
        password = urllib.parse.quote_plus('mongodb114514')
        client = pymongo.MongoClient('mongodb://%s:%s@42.192.36.136:2556' % (username, password))
        print(client.server_info())
        db = client["store"]
        gridfs_col = db["SmallFiles"]
        file_data = gridfs_col.find_one()
        print(file_data["file_data"])
        with open(out_name,'wb')as file_w:
            file_w.write(file_data["file_data"])


if __name__ == '__main__':
    a = MongoGridFS("")
    #ll = a.upLoadSmallFile("SmallFiles", "a.pdf", "")
    #print(ll)
    a.downLoadFile("pdf","a.pdf","c.pdf",1)
