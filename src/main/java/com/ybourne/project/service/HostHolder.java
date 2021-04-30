package com.ybourne.project.service;

import com.ybourne.project.model.User;
import com.ybourne.project.util.ConcurrentUtils;

public class HostHolder {

    public User getUser(){ return ConcurrentUtils.getHost();}
}
