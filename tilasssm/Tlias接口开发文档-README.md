# Tlias 智能学习辅助系统 - 接口开发文档

> 本文档整理了班级管理、学员管理、班级统计、学员统计四个模块的所有接口规范，供ClaudeCode进行接口开发参考。

---

## 一、班级管理模块 (Clazz Management)

### 1.1 班级列表查询（条件分页）

**接口名称**：班级列表条件分页查询

**请求方式**：`GET`

**请求路径**：`/clazzs`

**接口描述**：该接口用于班级列表数据的条件分页查询

#### 请求参数 (Query String)
| 参数名称 | 是否必须 | 示例 | 备注 |
|---------|---------|------|------|
| name | 否 | 黄埔一期 | 班级名称 |
| begin | 否 | 2023-01-01 | 范围匹配的开始时间(结课时间) |
| end | 否 | 2023-05-01 | 范围匹配的结束时间(结课时间) |
| page | 是 | 1 | 分页查询的页码，如果未指定，默认为1 |
| pageSize | 是 | 10 | 分页查询的每页记录数，如果未指定，默认为10 |

**请求示例**：
```
/clazzs?name=java&begin=2023-01-01&end=2023-06-30&page=1&pageSize=5
```

#### 响应数据
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| code | number | 必须 | 响应码，1 成功，0 失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 必须 | 返回的数据 |
| ├─ total | number | 必须 | 总记录数 |
| └─ rows | object[] | 必须 | 数据列表 |
| &nbsp;&nbsp;├─ id | number | 非必须 | id |
| &nbsp;&nbsp;├─ name | string | 非必须 | 班级名称 |
| &nbsp;&nbsp;├─ room | string | 非必须 | 班级教室 |
| &nbsp;&nbsp;├─ beginDate | string | 非必须 | 开课时间 |
| &nbsp;&nbsp;├─ endDate | string | 非必须 | 结课时间 |
| &nbsp;&nbsp;├─ masterId | number | 非必须 | 班主任(员工ID) |
| &nbsp;&nbsp;├─ masterName | string | 非必须 | 班主任姓名(员工姓名) |
| &nbsp;&nbsp;├─ createTime | string | 非必须 | 创建时间 |
| &nbsp;&nbsp;├─ updateTime | string | 非必须 | 更新时间 |
| &nbsp;&nbsp;└─ status | string | 非必须 | 状态 (未开班、已开班、已结课) |

---

### 1.2 删除班级

**接口名称**：删除班级

**请求方式**：`DELETE`

**请求路径**：`/clazzs/{id}`

**接口描述**：该接口用于删除班级信息

#### 请求参数 (路径参数)
| 参数名 | 类型 | 示例 | 是否必须 | 备注 |
|-------|------|------|---------|------|
| id | number | 1 | 必须 | 班级的ID |

**请求示例**：
```
/clazzs/5
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 1.3 添加班级

**接口名称**：添加班级

**请求方式**：`POST`

**请求路径**：`/clazzs`

**接口描述**：该接口用于添加班级信息

#### 请求参数 (JSON)
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| name | string | 必须 | 班级名称 |
| room | string | 必须 | 班级教室 |
| beginDate | string | 必须 | 开课时间 |
| endDate | string | 必须 | 结课时间 |
| masterId | number | 非必须 | 班主任 |
| subject | number | 必须 | 学科，1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式 |

**请求示例**：
```json
{
  "name": "JavaEE就业166期",
  "room": "101",
  "beginDate": "2023-06-01",
  "endDate": "2024-01-25",
  "masterId": 7,
  "subject": 1
}
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 1.4 根据ID查询班级

**接口名称**：根据ID查询班级

**请求方式**：`GET`

**请求路径**：`/clazzs/{id}`

**接口描述**：该接口用于根据主键ID查询班级的信息

#### 请求参数 (路径参数)
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| id | number | 必须 | 班级ID |

**请求示例**：
```
/clazzs/8
```

#### 响应数据
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| code | number | 必须 | 响应码，1 成功，0 失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 必须 | 返回的数据 |
| ├─ id | number | 必须 | id |
| ├─ name | string | 必须 | 班级名称 |
| ├─ room | string | 必须 | 班级教室 |
| ├─ beginDate | string | 必须 | 开课时间 |
| ├─ endDate | string | 必须 | 结课时间 |
| ├─ masterId | number | 必须 | 班主任(员工ID) |
| ├─ subject | number | 非必须 | 学科，1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式 |
| ├─ createTime | string | 必须 | 创建时间 |
| └─ updateTime | string | 必须 | 更新时间 |

---

### 1.5 修改班级

**接口名称**：修改班级

**请求方式**：`PUT`

**请求路径**：`/clazzs`

**接口描述**：该接口用于修改班级的数据信息

#### 请求参数 (JSON)
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| id | number | 必须 | id |
| name | string | 必须 | 班级名称 |
| room | string | 必须 | 班级教室 |
| beginDate | string | 必须 | 开课时间 |
| endDate | string | 必须 | 结课时间 |
| masterId | number | 必须 | 班主任ID(员工ID) |
| subject | number | 非必须 | 学科，1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式 |

**请求示例**：
```json
{
  "id": 8,
  "name": "JavaEE就业166期",
  "room": "101",
  "beginDate": "2023-06-01",
  "endDate": "2024-01-25",
  "masterId": 7,
  "subject": 1
}
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 1.6 查询所有班级

**接口名称**：查询所有班级

**请求方式**：`GET`

**请求路径**：`/clazzs/list`

**接口描述**：该接口用于查询所有班级信息

#### 请求参数
无

#### 响应数据
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| code | number | 必须 | 响应码，1 成功，0 失败 |
| msg | string | 非必须 | 提示信息 |
| data | object[] | 非必须 | 返回的数据 |
| ├─ id | number | 非必须 | id |
| ├─ name | string | 非必须 | 班级名称 |
| ├─ room | string | 非必须 | 班级教室 |
| ├─ beginDate | string | 非必须 | 开课时间 |
| ├─ endDate | string | 非必须 | 结课时间 |
| ├─ masterId | number | 非必须 | 班主任(员工ID) |
| ├─ subject | number | 非必须 | 学科，1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式 |
| ├─ createTime | string | 非必须 | 创建时间 |
| └─ updateTime | string | 非必须 | 更新时间 |

---

## 二、学员管理模块 (Student Management)

### 2.1 学员列表查询（条件分页）

**接口名称**：学员列表条件分页查询

**请求方式**：`GET`

**请求路径**：`/students`

**接口描述**：该接口用于学员列表数据的条件分页查询

#### 请求参数 (Query String)
| 参数名称 | 是否必须 | 示例 | 备注 |
|---------|---------|------|------|
| name | 否 | 张三 | 学员姓名 |
| degree | 否 | 1 | 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士) |
| clazzId | 否 | 2 | 班级ID |
| page | 是 | 1 | 分页查询的页码，如果未指定，默认为1 |
| pageSize | 是 | 10 | 分页查询的每页记录数，如果未指定，默认为10 |

**请求示例**：
```
/students?name=张三&degree=1&clazzId=2&page=1&pageSize=5
```

#### 响应数据
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| code | number | 必须 | 响应码，1 成功，0 失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 必须 | 返回的数据 |
| ├─ total | number | 必须 | 总记录数 |
| └─ rows | object[] | 必须 | 数据列表 |
| &nbsp;&nbsp;├─ id | number | 非必须 | id |
| &nbsp;&nbsp;├─ name | string | 非必须 | 姓名 |
| &nbsp;&nbsp;├─ no | string | 非必须 | 学号 |
| &nbsp;&nbsp;├─ gender | number | 非必须 | 性别(1: 男, 2: 女) |
| &nbsp;&nbsp;├─ phone | string | 非必须 | 手机号 |
| &nbsp;&nbsp;├─ degree | number | 非必须 | 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士) |
| &nbsp;&nbsp;├─ idCard | string | 非必须 | 身份证号 |
| &nbsp;&nbsp;├─ isCollege | number | 非必须 | 是否是院校学生 (1: 是, 0: 否) |
| &nbsp;&nbsp;├─ address | string | 非必须 | 联系地址 |
| &nbsp;&nbsp;├─ graduationDate | string | 非必须 | 毕业时间 |
| &nbsp;&nbsp;├─ violationCount | number | 非必须 | 违纪次数 |
| &nbsp;&nbsp;├─ violationScore | number | 非必须 | 违纪扣分 |
| &nbsp;&nbsp;├─ clazzId | number | 非必须 | 班级ID |
| &nbsp;&nbsp;├─ clazzName | string | 非必须 | 班级名称 |
| &nbsp;&nbsp;├─ createTime | string | 非必须 | 创建时间 |
| &nbsp;&nbsp;└─ updateTime | string | 非必须 | 更新时间 |

---

### 2.2 删除学员（批量删除）

**接口名称**：批量删除学员

**请求方式**：`DELETE`

**请求路径**：`/students/{ids}`

**接口描述**：该接口用于批量删除学员信息

#### 请求参数 (路径参数)
| 参数名 | 类型 | 示例 | 是否必须 | 备注 |
|-------|------|------|---------|------|
| ids | 数组 | 1,2,3 | 必须 | 学员的ID数组 |

**请求示例**：
```
/students/1,2,3
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 2.3 添加学员

**接口名称**：添加学员

**请求方式**：`POST`

**请求路径**：`/students`

**接口描述**：该接口用于添加学员信息

#### 请求参数 (JSON)
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| name | string | 必须 | 姓名 |
| no | string | 必须 | 学号 |
| gender | number | 必须 | 性别 |
| phone | string | 必须 | 手机号 |
| degree | number | 必须 | 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士) |
| clazzId | number | 必须 | 班级ID |
| idCard | string | 非必须 | 身份证号 |
| isCollege | number | 非必须 | 是否是院校学生 (1: 是, 0: 否) |
| address | string | 非必须 | 联系地址 |
| graduationDate | string | 非必须 | 毕业时间 |

**请求示例**：
```json
{
  "name": "阿大",
  "no": "2024010801",
  "gender": 1,
  "phone": "15909091235",
  "idCard": "159090912351590909",
  "isCollege": 1,
  "address": "昌平回龙观",
  "degree": 4,
  "graduationDate": "2024-01-01",
  "clazzId": 9
}
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 2.4 根据ID查询学员

**接口名称**：根据ID查询学员

**请求方式**：`GET`

**请求路径**：`/students/{id}`

**接口描述**：该接口用于根据主键ID查询学员的信息

#### 请求参数 (路径参数)
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| id | number | 必须 | 学员ID |

**请求示例**：
```
/students/8
```

#### 响应数据
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| code | number | 必须 | 响应码，1 成功，0 失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 必须 | 返回的数据 |
| ├─ id | number | 必须 | id |
| ├─ name | string | 必须 | 姓名 |
| ├─ no | string | 必须 | 学号 |
| ├─ phone | string | 必须 | 手机号 |
| ├─ gender | string | 必须 | 性别(1:男, 2:女) |
| ├─ degree | number | 必须 | 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士) |
| ├─ idCard | string | 非必须 | 身份证号 |
| ├─ isCollege | number | 非必须 | 是否是院校学生 (1: 是, 0: 否) |
| ├─ address | string | 非必须 | 联系地址 |
| ├─ graduationDate | string | 非必须 | 毕业时间 |
| ├─ violationCount | number | 必须 | 违纪次数 |
| ├─ violationScore | number | 必须 | 违纪扣分 |
| ├─ clazzId | number | 必须 | 班级ID |
| ├─ createTime | string | 必须 | 创建时间 |
| └─ updateTime | string | 必须 | 更新时间 |

---

### 2.5 修改学员

**接口名称**：修改学员

**请求方式**：`PUT`

**请求路径**：`/students`

**接口描述**：该接口用于修改学员的数据信息

#### 请求参数 (JSON)
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| id | number | 必须 | id |
| name | string | 必须 | 姓名 |
| no | string | 必须 | 学号 |
| phone | string | 必须 | 手机号 |
| gender | string | 必须 | 性别(1:男, 2:女) |
| degree | number | 必须 | 学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士) |
| idCard | string | 非必须 | 身份证号 |
| isCollege | number | 非必须 | 是否是院校学生 (1: 是, 0: 否) |
| address | string | 非必须 | 联系地址 |
| graduationDate | string | 非必须 | 毕业时间 |
| violationCount | number | 必须 | 违纪次数 |
| violationScore | number | 必须 | 违纪扣分 |
| clazzId | number | 必须 | 班级ID |

**请求示例**：
```json
{
  "id": 7,
  "name": "Locos",
  "no": "2023001010",
  "gender": 1,
  "phone": "13712345678",
  "degree": 5,
  "idCard": "110090110090110090",
  "isCollege": 0,
  "address": "回龙观东大街110号",
  "graduationDate": "2020-07-01",
  "violationCount": 0,
  "violationScore": 0,
  "clazzId": 2
}
```

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

### 2.6 违纪处理

**接口名称**：学员违纪处理

**请求方式**：`PUT`

**请求路径**：`/students/violation/{id}/{score}`

**接口描述**：该接口用于学员违纪扣分处理

#### 请求参数 (路径参数)
| 名称 | 类型 | 是否必须 | 备注 |
|-----|------|---------|------|
| id | number | 必须 | 学员ID |
| score | number | 必须 | 扣除分数 |

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |

---

## 三、班级统计模块 (Class Statistics)

### 3.1 班级人数统计

**接口名称**：班级人数统计

**请求方式**：`GET`

**请求路径**：`/report/studentCountData`

**接口描述**：统计每一个班级的人数

#### 请求参数
无

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object | 非必须 | 返回的数据 |
| ├─ clazzList | string[] | 必须 | 班级列表 |
| └─ dataList | number[] | 必须 | 每一个班级的人数列表 |

---

## 四、学员统计模块 (Student Statistics)

### 4.1 学员学历统计

**接口名称**：学员学历统计

**请求方式**：`GET`

**请求路径**：`/report/studentDegreeData`

**接口描述**：统计学员的学历信息

#### 请求参数
无

#### 响应数据
| 参数名 | 类型 | 是否必须 | 备注 |
|-------|------|---------|------|
| code | number | 必须 | 响应码，1 代表成功，0 代表失败 |
| msg | string | 非必须 | 提示信息 |
| data | object[] | 非必须 | 返回的数据 |
| ├─ name | string | 非必须 | 学历列表 |
| └─ value | number | 非必须 | 人数 |

---

## 五、统一响应格式说明

所有接口统一响应格式：

```json
{
  "code": 1,           // 1: 成功, 0: 失败
  "msg": "success",    // 提示信息
  "data": {}           // 返回数据
}
```

## 六、认证说明

用户登录成功后，系统会自动下发JWT令牌，在后续的每次请求中，都需要在请求头header中携带到服务端：
- 请求头名称：`token`
- 值：登录时下发的JWT令牌

如果检测到用户未登录，则直接响应 `401` 状态码。
