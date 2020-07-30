# 暑期PJ项目说明文档


### 一、PJ项目文件说明

> 本次PJ项目有JSP文件，Servlet等java文件，JS文件，CSS文件以及字体图标文件

#### 1. JSP文件概述

> 共有10个JSP文件，下面将逐个介绍

* **index.jsp**:

  <img src=".\网页截图\index.png" alt="未登录时的主页" style="zoom:60%;" />

  ​      index.jsp页面是网站的首页。自上而下是导航栏，轮播图，最新图片展示区域，页脚。导航栏会根据用户是否登录，来显示不同的样式，在未登录时右上角显示Login，点击后跳转到登录页面；登陆后显示用户的名字，并附带下拉列表。轮播图展示了被收藏最多的三张图片，图片展示区域为六张最新的图片。这些图片点击后都会跳转到相应的详情页。

* **detail.jsp**:

  <img src=".\网页截图\火狐截图_2020-07-29T06-41-34.897Z.png" style="zoom:50%;" />

  ​      detail.jsp页面时网站的图片详情页,在该页面展示某张图片的具体信息，并且在登录的情况下，可以进行图片的收藏和取消收藏，收藏或取消收藏时，下方按钮的内容会改变，同时LikeNumber的内容也会改变。具体的实现是通过ajax向Servlet发送数据，改变数据库。

* **search.jsp**:

  <img src=".\网页截图\search.png" style="zoom:40%;" />

  ​      search.jsp是网站的搜索页，自上而下为导航栏，搜索栏，图片展示区域，页码栏。导航栏同index.jsp界面相同。搜索栏为按标题和  按主题筛选，下方是图片排列规则，为按时间和按热度排序。图片展示区域在未搜索时展示按时间排序的所有图片，点击Filter后会展示筛选得到的图片；页码栏是根据得到的总的结果数量判断生成多少个页码，点击相应页码会展示相应页的图片。

* **login.jsp**:

  ![](.\网页截图\login.png)

  ​      login.jsp页面为网站的登录页面，可以通过点击主页，搜索页或详情页的Login跳转到该页面。用户可以通过输入账号和密码的方式登录。点击登陆后会通过ajax将加密的密码发送到后台与数据库进行对比，同时后台返回相应的信息，并执行相应的操作。在登录成功后会跳转到登录前的页面。在下方有跳转到注册页面的链接。

* **register.jsp**:

  ![](.\网页截图\register.png)

  ​       register.jsp为网站的注册页面。用户可以通过输入用户名，邮箱，密码进行注册，同时在前端会对密码强度进行检测（**密码必须大于5位才能触发**）并显示相应的结果；点击确认后会通过ajax将加密的密码发送到后端，后端予以相应的相应，并根据相应执行相应的操作。若成功注册，则直接登录并跳转到相应的页面。

* **myPhoto.jsp**:

  <img src=".\网页截图\myPhoto.png" style="zoom:80%;" />

  ​       myPhoto.jsp页面为网站的我的照片页，用于展示用户已经上传的照片。在这个页面用户可以对自己的照片进行修改，或删     除。点 击`Modify`后跳转到上传页面，可以对照片信息进行相应的修改。点击`delete`会删除数据库中的图片信息。

* **myFavor.jsp**:

  ![](.\网页截图\myFavor.png)

  ​       myFavor.jsp页面时网站的我的收藏页面，用于展示用户已经收藏的图片，同时也用于浏览好友收藏的页面。若是浏览自己的图片，点击`delete`会删除该图片的收藏；若是浏览好友则`delete`按钮不可用。在该页面的导航栏添加了`Track`的下拉菜单，在下拉菜单中会显示最近浏览过的图片详情页的标题，点击标题会跳转到相应的详情页。

* **upload.jsp**:

  <img src=".\网页截图\upload.png" style="zoom:67%;" />

  ​      upload.jsp是网站的上传和修改页。若为上传点击`Upload`选择图片后会展示在页面上，下方可以填充图片的相应信息；若为修改，进入页面就会显示图片对应的信息。在点击`submit`后前端会对页面信息进行初步判断，若信息不完整，会拒绝提交并提示相应信息。若信息完整，点击提交后会进入Servlet进行处理，保存或修改数据库和文件，完成后重定向到myPhoto.jsp页面。

* **friend.jsp**:

  <img src=".\网页截图\friend1.png" style="zoom:50%;" />

  ​      friend.jsp页面是网站好友页面，用于好友信息，添加好友等。网页自上而下为导航栏，状态设置栏以及好友功能栏。其中状态设置栏为设置好友是否能够查看自己收藏的图片；好友功能栏分为4栏，分别为`MyFriends`,`Message`,`Search`,`chat`;其中第一栏会显示已经添加的好友信息，可以点击`Look`查看好友的收藏（图片中未显示）。`Message`栏为显示好友请求信息的部分，用户可以选择接受或拒绝；`Search`栏位搜索好友并添加好友的部分。`chat`栏点击会跳转到聊天页面，可以与在线好友实时聊天。

* **chat.jsp**：

  ![](.\网页截图\chat.png)

  ​       chat.jsp为网站的聊天页，右面显示在线的好友，点击右侧在线好友名字，可与其进行在线聊天。同时可以与多名好友进行聊天，并能记录聊天信息。

#### 2. JavaBean概述

> 本次PJ中共设计了5个javaBean，下面一一介绍

* User类

  ​      用户类，主要用于传递和保存从数据库得到的用户的信息。主要有email userName password，UID，state，dateJoined属性。分别是用户邮箱，用户名，密码，用户的ID，账号创建的时间。

* Image类

  ​      图片类，用于传递和保存从数据库得到的图片信息。有imageID，title，description，cityCode，country_RegionCodeISO，path，content，date,UID，author，favorNumber，asciiName，countryName私有属性，分别为图片的ID，标题，简介，城市代码，地区代码，路径，主题，上传日期，上传用户的ID，作者，收藏数，城市名，国家名。
  
* City类

  ​      城市类，用于传递和保存从数据库获取的城市信息。有geoNameID，asciiName，country_RegionCodeISO等私有属性，分别为城市代码，城市名称，地区代码。
  
 * Country类

​            地区类，用于传递和保存从数据库获取的地区信息。有ISO，country_RegionName私有属性，分别为地区代码，地区名称。

#### 3. DAO概述

> PJ中设计了一个基础的JdbcDAO类和一个基础的JDBC的数据库连接池类，同时还有继承JdbcDAO类的UserDAO，ImageDAO，CityDAO，CountryDAO等子类。

 * **DataSource类**

   ​      数据库连接池类，类中有一个静态初始化块，用于连接数据库，配置连接池的信息。同时提     供一个静态方法getConnection方法，用于获取连接。


  * **JdbcDAO类**

    ​      该类是DAO的基础类，提供了基础的访问数据库的方法：batch，getForValue，get，getForList，update共5个方法，这些方法通过queryRunner来实现。


  * **UserDAO类**
  
    ​      UserDAO类继承JdbcDAO<User>类，并在原来类的基础上重新定义了许多的方法，以供在Servlet中更加方便的使用，这些方法主要是对用户信息的操作。
    
  * **ImageDAO类**

    ​      ImageDAO类继承JdbcDAO<Image>类，在原来类的基础上重新定义了关于图片信息的方法，以方便Servlet中对图片的操作。
    
  * **CityDAO类**

    ​      CityDAO继承JdbcDAO<City>类，在原来类的基础上定义了一个方法：getCityByCountry，该方法是通过地区代码获取地区下所有的城市，并封装成List<City>。
    
  * **CountryDAO类**

    ​      CountryDAO继承于JdbcDAO<Country>类，在原来类的基础上定义了getAllCountry，该方法获取所有的地区并封装成List<Country>返回。


#### 4. Servlet概述

> PJ中有12Servelt文件，分别用来处理前端发送的请求，同时返回数据信息。

* **LoginServlet**

  ​      用来处理login.jsp中js发送来的ajax请求，首先判断验证码是否正确，若正确在判断账号密码，并返回给前端是否成功的信息。
  
* **RegisterServlet**

  ​      用来处理register.jsp页面的ajax请求，首先判断验证码是否正确，若正确判断用户名是否存在，并返回相应的标志信息。
  
* **CodeServlet**

  ​      用来处理register.jsp和login.jsp的图片请求，并返回图片验证码数据。

* **DetailServlet**

  ​      在访问图片详情页时，会先进入DeatilServlet，Servlet会将该图片的完整信息存入session，在jsp语句输出。
  
* **SerachServlet**

  ​      SerachServlet通过接受ajax发送来的数据，判断执行什么方法·，并将得到的结果通过Gson转化为json的字符串返回给前端
  
  前端接受数据后将结果渲染到页面上。
  
* **HomeServlet**

  ​      HomeServlet接受index.jsp的ajax请求，返回最新最热的json字符串，前端接收后将其渲染到页面上。
  
* **MyFavorServlet**

  ​      在访问下拉列表的Collect时，会先进入到该Servlet，Servlet会将该用户收藏的所有照片信息存入到session中，在MyFavor.jsp页面中输出出来。
  
* **MyPhotoServlet**

  ​      在访问下拉列表的Photo时，会进入到该Servlet，Servlet将该用户上传的所有图片信息存入到session，到myPhoto.jsp
  
  页面输出出来
  
* **DeleteServlet**

  ​      接受myPhoto.jsp与myFavor.jsp页面发送的请求，将数据库中收藏图片的信息或上传图片的信息进行删除。
  
* **UploadServlet**

  ​      在修改图片或上传图片时，会进入该Servlet，先判断页面是上传还是修改，并返回相应的数据信息，在upload.jsp页面上显示。在点击提交后，会将数据上传到该Servlet，该页面会对传入的数据进行处理，若是修改，则直接修改数据库记录。如是上传，将图片保存到相应的位置，在修改数据库记录。
  
* **FriendServlet**

  ​      接受friend.jsp页面发送的ajax请求，根据请求的类型，执行相应的操作并返回相应的信息，并在前端页面渲染出来。
  
* **LogoutServlet**

  ​      在点击下拉列表中的logout后，该Servlet会将session中登录的用户信息清除，并跳转到网站首页。

### 二、PJ项目完成情况

#### 基础部分

PJ中的基础部分要求的功能已经全部实现，在文件说明中已经体现。

#### 拓展部分

* 实现了图片验证码功能[^ 验证码的具体实现]

* 实现了好友用户的实时聊天功能[^好友实时聊天的具体实现]

* 实现了云部署，将PJ项目部署到了阿里云服务器上

### 三、遇到的困难以及建议

#### 遇到的困难

* 不知如何在云服务器上配置javaweb环境，为了配置javaweb环境，我在网上查找了许多资料，由于各种说法不一致，花费了很多的时间去尝试，最终才配置成功。
* 刚开始写好友功能的时候，不知道如何架构好友系统的数据库结构，后面通过尝试建立一个比较完整的结构。

#### 建议

* 希望能够有云服务器系统操作的初步教程。

###  四、对数据库的改动

* 在travelimages表中添加了两列，字段为FavorNumber，Author；即各个图片收藏的人数和图片的作者。

* 创建了一个新的表friendship，用于存储朋友关系

  | id（主键） | friend1（UID1） | friend2（UID2） |
  | ---------- | --------------- | --------------- |
  |            |                 |                 |

* 创建了新的表identifyFriend，用于存储添加好友的请求

  | identifyID（主键） | pullUser（请求者UID） | getUser(被请求着UID) |
  | ------------------ | --------------------- | -------------------- |
  |                    |                       |                      |

  


### 五、脚注

[^图片验证码的具体实现]:首先创建了一个生成图片验证码的Code类，该类中有一个静态方法，该方法会随机生成一张图片验证码，并将图片数据和验证码都存入到Map中返回。同时创建一个CodeServlet类，用于响应请求，返回图片数据。在前端就用<img src="">来获取。
[^好友实时聊天的具体实现]:实时聊天功能的实现用到了WebSocket技术，WebSocket技术可以实现服务器端和客户端的“平等”对话，客户端可以向服务器端发送请求，服务器端也可以向客户端推送信息。在PJ中建立了一个Socket类，实现了onOpen（当有客户端连接时调用），onClose（连接关闭时调用），onMessage（接受到消息时调用）。在该类中建立一个set用于储存创建的websocket实例。在接收到前端发送的消息后看，获得要发送的人，并向其发送该消息。同时客户端通过sessionScope保存与不同好友的聊天记录。