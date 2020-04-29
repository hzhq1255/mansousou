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

### 5.ReadController

#### 	/getAllReadByUserId  

**Desc**: 通过userid获得阅读记录

**Content-type: application/x-www-form-urlencoded**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":{
        "totalPages": 1,
        "totalElements":1,
        "pageSize":10,
        "currentPage":1,
        "content":[
            " Read Object List"
        ]
    }
}
```



| 参数        | 类型    | 可否为空 |
| ----------- | ------- | -------- |
| userId      | Integer | 否       |
| currentPage | Integer | 否       |
| pageSize    | Integer | 否       |

#### 	/getAllRead

**Desc**: 获得所有阅读记录

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": sucess,
    "data":{
        "totalPages": 1,
        "totalElements":1,
        "pageSize":10,
        "currentPage":1,
        "content":[
            " Read Object List"
        ]
    }
}
```


| 参数        | 类型    | 可否为空 |
| ----------- | ------- | -------- |
| currentPage | Integer | 否       |
| pageSize    | Integer | 否       |

#### 	/addRead

**Desc**: 添加阅读记录

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": "sucess",
    "data": "if successfully add then return readId"
}
```

| 参数      | 类型    | 可否为空 | 描述     |
| --------- | ------- | -------- | -------- |
| userId    | Integer | 否       |          |
| comicId   | String  | 否       |          |
| title     | String  | 否       | 漫画标题 |
| chapterid | String  | 可       |          |
| chapter   | String  | 可       | 章节名称 |
| url       | String  | 可       | 章节链接 |

#### /updateRead

**Desc**: 通过id删除记录

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": "sucess",
    "data": "if successfully update then return readId"
}
```

| 参数      | 类型    | 可否为空 | 描述       |
| --------- | ------- | -------- | ---------- |
| readId    | Integer | 否       |            |
| chapterid | String  | 否       | 改变的章节 |
| chapter   | String  | 否       | 章节名称   |
| url       | String  | 否       | 章节链接   |

#### /deleteReadByReadId

**Desc**: 通过id删除记录

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": "if successfully delete then return readId"
}
```

| 参数   | 类型    | 可否为空 | 描述               |
| ------ | ------- | -------- | ------------------ |
| readId | Integer | 否       | 需要被删除的记录id |

#### /deleteAllReadByUserId

**Desc**: 删除某一用户下的所有记录

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": "if successfully delete then return a list<Integer> readIds"
}
```

| 参数   | 类型    | 可否为空 | 描述 |
| ------ | ------- | -------- | ---- |
| userId | Integer | 否       |      |



#### /recordRead

**Desc**: 记录阅读记录，传入 userId,comicId，如果已存在，只更新时间，如果不存在，创建记录，如果传入完整参数，并且存在更新记录，其他报错A

**Content-type:** application/x-www-form-urlencoded

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": "if successfully record then return readId"
}
```
| 参数      | 类型    | 可否为空 | 描述       |
| --------- | ------- | -------- | ---------- |
| userId    | Integer | 否       |            |
| comicId          | String | 否 |            |
| title | String | 可 | 最好带上，不能在没有标题的时候插入 |
| chapterid | String  | 可       | 改变的章节 |
| chapter   | String  | 可       | 章节名称   |
| url       | String  | 可       | 章节链接   |
