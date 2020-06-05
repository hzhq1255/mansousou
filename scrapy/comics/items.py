# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class ComicsItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    pass

class ComicsDetailItem(scrapy.Item):
    title = scrapy.Field()
    url = scrapy.Field()
    pics= scrapy.Field()
    rate = scrapy.Field()
    desc = scrapy.Field()
    author = scrapy.Field()
    hot = scrapy.Field()
    collect = scrapy.Field()
    id=scrapy.Field()
    chapterlist=scrapy.Field()
    status=scrapy.Field()
    genre=scrapy.Field()
    chapterUrl=scrapy.Field()
    chapterId=scrapy.Field()
