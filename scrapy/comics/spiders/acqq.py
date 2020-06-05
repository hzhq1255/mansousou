# -*- coding: utf-8 -*-
import scrapy
from scrapy.http import Request
from urllib import parse
from comics.items import ComicsDetailItem
import re
class AcqqSpider(scrapy.Spider):
    name = 'acqq'
    allowed_domains = ['ac.qq.com']
    start_urls = ['https://ac.qq.com/Comic/all/search/time/page/1']
    count=1

    def parse(self, response):
        post_urls=response.css(".ret-search-result .ret-search-list .ret-works-cover  .mod-cover-effect::attr(href)").extract()

        for post_url in post_urls:
            post_url=parse.urljoin(response.url, post_url)
            tag = response.xpath('/html/body/div[3]/div[2]/div/div[2]/ul/li[1]/div[2]/p[2]/a/text()').extract()
            yield Request(url=post_url,meta={"tags":tag},callback=self.parse_detail)

        for i in range(2,356):
            next_url="https://ac.qq.com/Comic/all/search/time/page/"+str(i)
            yield Request(url=next_url, callback=self.parse)
    def parse_detail(self,response):
        comicsItem = ComicsDetailItem()
        tags=response.meta["tags"]
        title=response.xpath('//*[@id="special_bg"]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/h2/strong/text()').extract()[0]
        pics=response.css('.works-cover a img::attr(src)').extract()[0]
        rate=response.xpath('//*[@id="special_bg"]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/p/strong/text()').extract()
        if len(rate):
            rate=rate[0]
        else:
            rate="无评分"
        desc=response.xpath('//*[@id="special_bg"]/div[3]/div[1]/div[1]/div[2]/div[1]/p[2]/text()').extract()[0].strip()
        author=response.xpath('//*[@id="special_bg"]/div[3]/div[2]/div[1]/div[1]/dl/dt/a/text()').extract()[0]
        hot=response.xpath('//*[@id="special_bg"]/div[3]/div[1]/div[1]/div[2]/div[1]/p[1]/span[2]/em/text()').extract()[0]
        collect=response.xpath('//*[@id="coll_count"]/text()').extract()[0]
        status=response.xpath('//*[@id="special_bg"]/div[3]/div[1]/div[1]/div[1]/label/text()').extract()[0]
        chapterlist=response.css('.chapter-page-all a::attr(title)').extract()
        chapterUrl=response.css(".chapter-page-all a::attr(href)").extract()
        for i in range(len(chapterUrl)):
            chapterUrl[i]="https://ac.qq.com"+chapterUrl[i]
        id=response.url
        id=id[37:]
        comicsItem["chapterUrl"]=chapterUrl
        comicsItem["genre"]=tags
        comicsItem["chapterlist"]=chapterlist
        comicsItem["id"] = id
        comicsItem["title"]=title
        comicsItem["pics"] =pics
        comicsItem["rate"] =rate
        comicsItem["desc"] =desc
        comicsItem["author"] =author
        comicsItem["hot"] =hot
        comicsItem["collect"] =collect
        comicsItem["url"] =response.url
        comicsItem["status"]=status
        yield comicsItem

        pass
