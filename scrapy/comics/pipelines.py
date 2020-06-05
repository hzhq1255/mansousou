# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import datetime
from twisted.enterprise import adbapi
import MySQLdb
import MySQLdb.cursors
class ComicsPipeline(object):
    def process_item(self, item, spider):
        return item



class MysqlPipeline(object):
    def __init__(self):
        self.conn=MySQLdb.connect('106.54.81.203','root','123456','comics',charset="utf8",use_unicode=True)
        self.cursor=self.conn.cursor()
    def process_item(self, item, spider):
        sql="""
            insert into comicsDetail (title,id) values (%s,%s)
        """
        self.cursor.execute(sql,(item["title"],item["id"]))
        self.conn.commit()

class MysqlTwistedPipeline(object):
    def __init__(self,dbpool):
        self.dbpool=dbpool

    @classmethod
    def from_settings(cls,settings):
        dbparms=dict(
            host=settings["MYSQL_HOST"],
            db=settings["MYSQL_DBNAME"],
            user=settings["MYSQL_USER"],
            passwd=settings["MYSQL_PWD"],
            charset="utf8",
            cursorclass=MySQLdb.cursors.DictCursor,
            use_unicode=True
        )


        dbpool=adbapi.ConnectionPool("MySQLdb",**dbparms)

        return cls(dbpool)
    def process_item(self, item, spider):
        query=self.dbpool.runInteraction(self.do_insert,item)
        query.addErrback(self.handle_error)

    def handle_error(self,failure):
        print(failure)

    def do_insert(self,cursor,item):
        nowTime=datetime.datetime.now()
        now = nowTime.strftime("%Y-%m-%d %H:%M:%S")
        sql_check="""
            select * from `comics`.`chapter` where id=%s
        """
        sql="""
            INSERT INTO `comics`.`chapter` (`id`,`comicsId`, `chapter`,`no`,`url` ,`createTime`, `updateTime`) VALUES (%s,%s,%s,%s,%s,%s,%s);
        """

        sql_update = """
                    UPDATE  `comics`.`chapter` SET `id`=%s,`comicsId`=%s, `chapter`=%s,`no`=%s,`url`=%s, `updateTime`=%s  where id=%s
        """


        for i in range(len(item["chapterlist"])):
            # print("结果 "+item["id"]+" 长度 "+str(len(item["chapterlist"])))
            id = item["id"] + "_" + str(i + 1)
            id_=(id,)
            cursor.execute(sql_check,id_)
            result=cursor.fetchall()
            if len(result)==0:
                cursor.execute(sql, (id, item["id"], item["chapterlist"][i], i + 1, item["chapterUrl"][i], now,now))
            else:
                cursor.execute(sql_update, (id, item["id"], item["chapterlist"][i], i + 1, item["chapterUrl"][i], now,id))

        genre=""
        for i in range(len(item["genre"])):
            if (i==0):
                genre = item["genre"][i]
            else:
                genre=item["genre"][i]+","+genre
        sql2_check="""
            select * from `comics`.`comicsDetail` where id =%s
        """
        sql2_update="""
                UPDATE  `comics`.`comicsDetail` SET `id`=%s, `title`=%s, `pics`=%s, `url`=%s, `rate`=%s, `desc`=%s, `author`=%s, `hot`=%s, `collect`=%s, `status`=%s,`genre`=%s , `updateTime`=%s where id=%s
        """
        sql2 = """
                          INSERT INTO `comics`.`comicsDetail` (`id`, `title`, `pics`, `url`, `rate`, `desc`, `author`, `hot`, `collect`, `status`,`genre` ,`createTime`, `updateTime`) VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
                      """
        cid=(item["id"],)
        cursor.execute(sql_check,cid)
        result2=cursor.fetchall()
        if len(result2) == 0:
            cursor.execute(sql2, (
                item["id"], item["title"], item["pics"], item["url"], item["rate"], item["desc"], item["author"],
                item["hot"],
                item["collect"], item["status"], genre, now, now))
        else:
            cursor.execute(sql2_update,(
                item["id"], item["title"], item["pics"], item["url"], item["rate"], item["desc"], item["author"],
                item["hot"],
                item["collect"], item["status"], genre,  now,item["id"]))

