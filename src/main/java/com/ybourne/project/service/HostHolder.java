package com.ybourne.project.service;

import com.ybourne.project.model.User;
import com.ybourne.project.util.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser(){ return ConcurrentUtils.getHost();}

    public void setUser(User user) { ConcurrentUtils.setHost(user); }
}
