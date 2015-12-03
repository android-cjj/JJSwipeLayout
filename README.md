JJSwipeLayout
=============================================
The Sample Swipe Layout!
---------------------------------------------

滑动删除的比较出名的的有代码家的[https://github.com/daimajia/AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout)和Mr.Bao的[https://github.com/baoyongzhang/SwipeMenuListView](https://github.com/baoyongzhang/SwipeMenuListView),但是，代码家的那个功能太强大了，我之前用了，有点小bug,看着那么多的代码，心好累，自己改不了bug,我发现如果你不是要求那么多，只是像qq那样的简单功能的话，完全可以自己实现，Mr.bao那个是listView的，如果项目中是recyclerView的朋友要实现，可能又要大改了。。。
所以，我自己又重复造轮子了，请原谅我的无理取闹，明明别人已经写好了，我还没事找事，我只是实现了lv和rv都可以适应，一个方向的SwipeLayout...

效果：
----------------------------------------

![](https://camo.githubusercontent.com/df11f2a298e5c3aa843f63e81516cdb01e04e019/687474703a2f2f7777332e73696e61696d672e636e2f6d773639302f36313064633033346a7731656a703362736b36747667323039353032626a74632e676966)

(原谅我无耻的偷了代码家的动图，啊哈哈)

Base layout
---------------------------
![](https://github.com/android-cjj/JJSwipeLayout/blob/master/img/a.jpg)

swipe for listview
-------------------------------
![](https://github.com/android-cjj/JJSwipeLayout/blob/master/img/b.jpg)

swipe for recyclerview
--------------------
![](https://github.com/android-cjj/JJSwipeLayout/blob/master/img/c.jpg)

使用说明
--------------------------------------------------

```xml
<com.cjj.swipe.JJSwipeLayout
    android:id="@+id/swipelayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical"
        android:gravity="center"
        android:layout_width="match_parent"
        android:layout_height="80dp">
          <!--这一层是内容界面，外层包围内容的需要是ViewGroup，所以可以是Framelayout,RelativeLayout-->
    </LinearLayout>

    <LinearLayout
        android:id="@+id/bottom_wrapper_2"
        android:layout_width="210dp"
        android:layout_height="80dp">
       <!--这一层是滑动的菜单界面,可以添加删除，喜欢等功能view-->
    </LinearLayout>
</com.cjj.swipe.JJSwipeLayout>
```
具体你可以看源码demo,使用很简单的，呵呵。

其他
-------------------------------------------------------
jjSwipeLayout.isOpen()//是判断打开的状态

jjSwipeLayout.setOnSwipeBackListener（）//监听swipe

jjSwipeLayout.close()//关闭swipelayout

jjSwipeLayout.open()//打开swipelayout

jjSwipeLayout.setAlphaAnim(true);//设置菜单滑动出来的时候有透明动画效果


If you want to support me,you can follow me on GitHub: https://github.com/android-cjj.



License
=======

    The MIT License (MIT)

	Copyright (c) 2015 android-cjj

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.









