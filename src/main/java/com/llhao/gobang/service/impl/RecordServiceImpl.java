package com.llhao.gobang.service.impl;

import com.llhao.gobang.dao.IRecordDao;
import com.llhao.gobang.dao.IUserDao;
import com.llhao.gobang.dao.jo.UpdateUserJO;
import com.llhao.gobang.entity.Record;
import com.llhao.gobang.service.IRecordService;
import com.llhao.gobang.service.po.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by luohao on 2017/6/2.
 */
@Service
public class RecordServiceImpl implements IRecordService {
    @Autowired
    private IRecordDao recordDao;
    @Autowired
    private IUserDao userDao;
    @Override
    public int save(Game game) {
        if(game.isSave()){
            throw new RuntimeException("this game has been save");
        }
        game.setSave(true);
        //save record
        Record record = new Record();
        record.setBlack(game.getBlack().getId());
        record.setWhite(game.getWhite().getId());
        record.setRtime(new Timestamp(System.currentTimeMillis()));
        record.setRwin(game.getWin());
        record.setData(game.getDate());
        recordDao.insertRecord(record);
        //update user black
        UpdateUserJO jo = new UpdateUserJO();
        jo.setUserId(game.getBlack().getId());
        jo.setType(game.getWin());
        userDao.updateUser(jo);
        //update user white
        jo.setUserId(game.getWhite().getId());
        jo.setType(-game.getWin());
        userDao.updateUser(jo);
        System.out.println("SAVE RECORD");
        return record.getRid();
    }
}
