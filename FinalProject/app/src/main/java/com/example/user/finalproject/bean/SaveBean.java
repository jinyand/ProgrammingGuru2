package com.example.user.finalproject.bean;

import android.support.v4.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-07-27.
 */

public class SaveBean extends CommonBean {

    private LostBean lostBean;
    private FoundBean foundBean;
    // FoundBean의 정보를 List로
    private List<FoundBean> foundBeanList = new ArrayList<FoundBean>();
    // LostBean의 정보를 List로
    private List<LostBean> lostBeanList = new ArrayList<LostBean>();



    public SaveBean() {
        lostBean = new LostBean();
        foundBean = new FoundBean();
    }

    public FoundBean getFoundBean() {
        return foundBean;
    }
    public void setFoundBean(FoundBean foundBean) {
        this.foundBean = foundBean;
    }
    public LostBean getLostBean() {
        return lostBean;
    }
    public void setLostBean(LostBean lostBean) {
        this.lostBean = lostBean;
    }
    public List<FoundBean> getFoundBeanList() {
        return foundBeanList;
    }
    public void setFoundBeanList(List<FoundBean> foundBeanList) {
        this.foundBeanList = foundBeanList;
    }
    public List<LostBean> getLostBeanList() {
        return lostBeanList;
    }
    public void setLostBeanList(List<LostBean> lostBeanList) {
        this.lostBeanList = lostBeanList;
    }
}
