package com.cjj.adapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public interface DataHandler<J> {

    public void updateData(List<J> list);

    public void updateData(J[] array);

    public void removeData(int pos);

    public void removeData(J item);

    public void addData(List<J> list);

    public void addData(J[] arrays);

    public void addData(J item);

}
