# PulltorefreshListview

##  1. ��Module�µ�build.gradle���������
### Step 1. Add the JitPack repository to your build file
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
### Step 2. Add the dependency
    dependencies {
	        compile 'com.github.Tudie:PulltorefreshListview:v1.0.0'
	}

##  2. �ڲ����ļ������PullToRefreshRecyclerView�ؼ�
    <com.handmark.pulltorefresh.library.PullToRefreshListView
        android:id="@+id/pullToRefreshRV"
        android:layout_width="368dp"
        android:layout_height="495dp"
        ptr:ptrAnimationStyle="flip"
        pulltorefresh:ptrAnimationStyle="flip"
        pulltorefresh:ptrDrawable="@drawable/xlistview_load_progressbg"
        pulltorefresh:ptrDrawableEnd="@drawable/pulltorefresh_head_indicate"
        pulltorefresh:ptrDrawableStart="@drawable/pulltorefresh_head_indicate" />

##  3. ��ʼ��PullToRefreshRecyclerView���������Ժͻص�
    //�����Ƿ�������ˢ���������ظ���
     pullToRefreshRV.setMode(PullToRefreshBase.Mode.BOTH);
    //����ˢ�»ص�
     pullToRefreshRV.setOnRefreshListener(this);

##  4.����ˢ�¼����߼�
    @Override
    public void onRefresh() {
        pullToRefreshRV.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshRV.setRefreshComplete();
                //ģ��û�����ݵ����
                data.clear();
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
        pullToRefreshRV.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshRV.setLoadMoreComplete();
                //ģ��������ݵ����
                int size = data.size();
                for (int i = size; i < size + 4; i++) {
                    data.add("" + i + i + i + i);
                }
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }