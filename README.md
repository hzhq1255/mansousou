[TOC]

## 后端接口

api地址 http://localhost:8443/api

响应类 Result

| 变量 | 类型    | 说明       |
| ---- | ------- | ---------- |
| code | Integer | 响应状态码 |
| msg  | String  | 响应消息   |
| data | Object  | 响应数据   |



### 1.UserController

#### /login

**Desc**: 登录成功返回 userId

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": " userId "
}
```



| 参数     | 类型   | 可否为空 | 描述 |
| -------- | ------ | -------- | ---- |
| username | String | 否       |      |
| password | String | 否       |      |

#### /reg

**Desc**:  注册

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": " 注册成功 "
}
```

| 参数      | 类型   | 可否为空 | 描述 |
| --------- | ------ | -------- | ---- |
| username  | String | 否       |      |
| password1 | String | 否       |      |
| password2 | String | 否       |      |

#### /getUserInfo

**Desc**: 获取用户的收藏，阅读，搜索记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

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
            "user": {
             "read": {},
             "collect": {},
             "search": {}
            }
        ]
    }
}
```



| 参数   | 类型    | 可否为空 | 描述 |
| ------ | ------- | -------- | ---- |
| userId | Integer | 否       |      |

### 2.ComicController

#### /searchComic

**Desc:** es查询漫画，匹配漫画标题和简介

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            "list comics"
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述     |
| ----------- | ------- | -------- | -------- |
| keyword     | String  | 否       | 查询内容 |
| currentPage | Integer | 否       |          |
| pageSize    | Integer | 否       |          |

#### /getAllComic

**Desc:** 获取所有漫画

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            "list comics"
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### /getComicByComicId

**Desc:** 通过id获取漫画

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":{
        "Comic Object"
    }
}
```

| 参数    | 类型   | 可否为空 | 描述 |
| ------- | ------ | -------- | ---- |
| comicId | String | 否       |      |




### 3.CollectController

#### /getAllCollectByUserName

**Desc**: 通过userName获得收藏记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            " Collect Object List"
        ]
    }
}
```

| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| userName    | String | 否       |      |
| currentPage | String | 否       |      |
| pageSize    | String | 否       |      |

#### /getAllCollectByUserId
**Desc**: 通过userId获得收藏记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            " Collect Object List"
        ]
    }
}
```

| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| userId    | Integer | 否       |      |
| currentPage | String | 否       |      |
| pageSize    | String | 否       |      |

#### /getAllCollect
**Desc**: 获取所有收藏记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            " Collect Object List"
        ]
    }
}
```
| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| currentPage | String | 否       |      |
| pageSize    | String | 否       |      |

#### /addCollect
**Desc**: 获取所有收藏记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":"add CollectId"
}
```
| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| userId    | Integer | 否       |      |
| comicId    | String | 否       |      |
| title | String | 否       |漫画名称      |
| url    | String | 否       |漫画链接      |
| pics | String | 否 |漫画封面 |

#### /updateCollect
**Desc**: 修改收藏记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":"update CollectId"
}
```
| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| collectId    | Integer | 否       |      |
| comicId    | String | 否       |      |
| title | String | 否       |漫画名称      |
| url    | String | 否       |漫画链接      |
| pics | String | 否 |漫画封面 |

#### /deleteAllCollectByUserId
**Desc**: 删除用户的所有收藏

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":"delete collectIds"
}
```
| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| userId    | Integer | 否       |      |

#### /deleteCollectByCollectId
**Desc**:  删除单个收藏

**Content-type: application/x-www-form-urlencoded**

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data":"delete CollectId"
}
```
| 参数        | 类型   | 可否为空 | 描述 |
| ----------- | ------ | -------- | ---- |
| collectId    | Integer | 否       |      |

### 4.ChapterController

#### /getAllChapterByComicId

**Desc**: 通过漫画 id 获取所有章节信息

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            " Chapter Object List"
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| comicId     | Integer | 否       |      |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### /getAllChapter

**Desc**: 通过漫画 id 获取所有章节信息

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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
            " Chapter Object List"
        ]
    }
}
```
| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

### 5.ReadController

#### 	/getAllReadByUserId  

**Desc**: 通过userid获得阅读记录

**Content-type: application/x-www-form-urlencoded**

**Http:** **get，post**

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

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| userId      | Integer | 否       |      |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### 	/getAllRead

**Desc**: 获得所有阅读记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **get，post**

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


| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### 	/addRead

**Desc**: 添加阅读记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

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
| pics      | String  | 否       | 漫画封面 |
| chapterId | String  | 可       |          |
| chapter   | String  | 可       | 章节名称 |
| url       | String  | 可       | 章节链接 |

#### /updateRead

**Desc**: 通过id删除记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

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
| chapterId | String  | 否       | 改变的章节 |
| chapter   | String  | 否       | 章节名称   |
| url       | String  | 否       | 章节链接   |

#### /deleteReadByReadId

**Desc**: 通过id删除记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

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

**Http:** **post**

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

**Desc**: 记录阅读记录，传入 userId,comicId，如果已存在，只更新时间，如果不存在，创建记录，如果传入完整参数，并且存在更新记录，其他报错A10

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

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
| title | String | 否 | 不能在没有标题的时候插入 |
| pics | String | 否 | 漫画封面 |
| chapterId | String  | 可       | 改变的章节 |
| chapter   | String  | 可       | 章节名称   |
| url       | String  | 否      | 章节链接   |

### 6. SearchController

#### /getHotSearch

**Desc**:  获取总的搜索热度，按照热度降序排序

**Content-type:** application/x-www-form-urlencoded

**Http:** **get，post**

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
            {
                "keyword": "XXX",
                "count": 3
            }
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### /getAllSearch

**Desc**:  获取总的搜索热度，按照热度降序排序

**Content-type:** application/x-www-form-urlencoded

**Http:** **get，post**

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
            "search object list"
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### /getAllSearchByUserId
**Desc**:  获取用户的搜索记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **get，post**

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
            "search object list"
        ]
    }
}
```

| 参数        | 类型    | 可否为空 | 描述 |
| ----------- | ------- | -------- | ---- |
| userId      | Integer | 否       |      |
| currentPage | Integer | 否       |      |
| pageSize    | Integer | 否       |      |

#### /clearAllSearchBySearchId

**Desc**:  删除某一搜索记录

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": " searchId"
}
```

| 参数     | 类型    | 可否为空 | 描述       |
| -------- | ------- | -------- | ---------- |
| searchId | Integer | 否       | 搜索记录ID |

#### /clearAllSearchByUserId

**Desc**:  清空用户的搜索历史

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

**Return:**

```json
{
    "code": 200,
    "msg": "success",
    "data": "userId"
}
```

| 参数   | 类型    | 可否为空 | 描述 |
| ------ | ------- | -------- | ---- |
| userId | Integer | 否       |      |

#### /addSearch

**Desc**:  清空用户的搜索历史

**Content-type:** application/x-www-form-urlencoded

**Http:** **post**

**Return:**

```json
{
    "code":200,
    "msg": "success",
    "data": "add a Search"
}
```

| 参数    | 类型    | 可否为空 | 描述     |
| ------- | ------- | -------- | -------- |
| keyword | String  | 否       | 搜索内容 |
| userId  | Integer | 可       |          |

