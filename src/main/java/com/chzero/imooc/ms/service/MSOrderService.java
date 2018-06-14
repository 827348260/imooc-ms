package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.POJO.MSOrder;
import com.chzero.imooc.ms.dao.MSOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-14 10:49
 * @email 827348260@qq.com
 * @description
 */
@Service
public class MSOrderService{

    @Autowired
    private MSOrderDAO msOrderDAO;

    public MSOrder getMSOrderByIdUserIdGoods(long idUser, long idGoods){
       return this.msOrderDAO.getMSOrderByIdUserIdGoods(idUser, idGoods);
    }

}
