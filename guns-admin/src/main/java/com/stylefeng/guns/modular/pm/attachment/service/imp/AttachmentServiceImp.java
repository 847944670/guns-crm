package com.stylefeng.guns.modular.pm.attachment.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.pm.attachment.dao.AttachmentDao;
import com.stylefeng.guns.modular.pm.attachment.entity.Attachment;
import com.stylefeng.guns.modular.pm.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;

/**
 * @author sunwei
 * @date 2019/6/24
 */
@Service
public class AttachmentServiceImp extends ServiceImpl<AttachmentDao,Attachment> implements AttachmentService  {
}