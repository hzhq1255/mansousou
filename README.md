[TOC]

## 接口

api地址 http://localhost:8443/api

响应类 Result

| 变量 | 类型    | 说明       |
| ---- | ------- | ---------- |
| code | Integer | 响应状态码 |
| msg  | String  | 响应消息   |
| data | Object  | 响应数据   |



### 1.UserController

| 接口     | 参数                                              | 返回数据                          |
| -------- | ------------------------------------------------- | --------------------------------- |
| /login   | String userame,String password                    | 登录成功返回带有success信息的响应 |
| /reg     | String username,String password1,String password2 | 注册成功返回带有success信息的响应 |
| /getInfo | 无                                                | 无                                |

### 2.ComicController

| 接口         | 参数                                               | 返回数据             |
| ------------ | -------------------------------------------------- | -------------------- |
| /searchComic | String keyword,Integer currentPage,Intger pageSize | 通过es查询成功的漫画 |
| /getAllComic | Integer currentPage,Intger pageSize                | 数据库中的所有漫画   |

### 3.CollectController

| 接口                     | 参数                                                 | 返回数据                   |
| ------------------------ | ---------------------------------------------------- | -------------------------- |
| /getAllCollectByUserName | String userName,Integer currentPage,Integer pageSize | 通过用户名获得所有收藏数据 |
| /getAllCollectByUserId   | Integer userId,Integer currentPage,Integer pageSize  | 通过用户id获得所有收藏数据 |

### 4.ChapterController

| 接口                       | 参数                                                 | 返回数据                          |
| -------------------------- | ---------------------------------------------------- | --------------------------------- |
| /searchAllChapterByComicId | Integer comicId,Integer currentPage,Integer pageSize | 通过es查询comicId获得所有章节数据 |
| /getAllChapter             | Integer currentPage,Integer pageSize                 | 从数据库获取所有章节              |

