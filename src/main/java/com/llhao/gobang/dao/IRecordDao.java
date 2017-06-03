package com.llhao.gobang.dao;

import com.llhao.gobang.entity.Record;

import java.util.List;

/**
 * Created by luohao on 2017/6/1.
 */
public interface IRecordDao {
    int insertRecord(Record record);
    List<Record> userRecord(int id);
}
