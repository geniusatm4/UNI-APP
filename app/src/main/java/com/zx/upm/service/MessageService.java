package com.zx.upm.service;

import com.zx.upm.face.vo.MessageSearchResult;
import com.zx.upm.face.vo.MessageVO;

public class MessageService {

    public MessageSearchResult getMessages() {
        MessageSearchResult res = new MessageSearchResult();

        for (int i = 0; i < 20; i++) {
            MessageVO vo = new MessageVO();
            vo.setContent("我是告警内容11111,");
            res.getMessages().add(vo);
        }

        return res;
    }
}
