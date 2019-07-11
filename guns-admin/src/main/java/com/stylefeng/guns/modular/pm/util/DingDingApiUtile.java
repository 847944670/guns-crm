//package com.stylefeng.guns.pm.util;
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiDepartmentListRequest;
//import com.dingtalk.api.request.OapiGettokenRequest;
//import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
//import com.dingtalk.api.request.OapiUserSimplelistRequest;
//import com.dingtalk.api.response.OapiDepartmentListResponse;
//import com.dingtalk.api.response.OapiGettokenResponse;
//import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
//import com.dingtalk.api.response.OapiUserSimplelistResponse;
//import com.taobao.api.ApiException;
//
///**
// *接入钉钉Api
// */
//public class DingDingApiUtile {
//    private final static String APPKEY ="";
//
//    private final static String APPSECRET="";
//
//    private final static Long AGENTID = 0L;
//
//    //获取token
//    public static String getToken (){
//
//        DefaultDingTalkClient client = new
//                DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//        OapiGettokenRequest request = new OapiGettokenRequest();
//        request.setAppkey(APPKEY);
//        request.setAppsecret(APPSECRET);
//        request.setHttpMethod("GET");
//        try {
//            OapiGettokenResponse response = client.execute(request);
//            return response.getAccessToken();
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //获取部门列表
//    public static Object getDepartment(){
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
//        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
//        //获取根部门下所有部门列表  根部门的部门id为1
//        request.setId("1");
//        request.setHttpMethod("GET");
//
//        try {
//            OapiDepartmentListResponse response = client.execute(request, DingDingApiUtile.getToken());
//            return response;
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //获取部门下的所有用户列表
//    public static Object getDepartmentUser(Long departmentId){
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
//        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
//        request.setDepartmentId(departmentId);
//        request.setOffset(0L);
//        request.setSize(10L);
//        request.setHttpMethod("GET");
//
//        try {
//            OapiUserSimplelistResponse response = client.execute(request, DingDingApiUtile.getToken());
//            return response;
//        } catch (ApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //给用户推送消息（文字消息）
//    public static Object pushUser(String userIds,String content){
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
//
//        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
//        request.setUseridList(userIds);
//        request.setAgentId(AGENTID);
//        request.setToAllUser(false);
//
//        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
//        msg.setMsgtype("text");
//        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
//        msg.getText().setContent(content);
//        request.setMsg(msg);
//
//        try {
//            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,DingDingApiUtile.getToken());
//            return response;
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}