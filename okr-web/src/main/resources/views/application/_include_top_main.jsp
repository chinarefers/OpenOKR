<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/_include/_html_doctype.jsp"%>
<html>
<head>
    <%@include file="/views/_include/_html_meta.jsp"%>
    <%@include file="/views/_include/_html_title.jsp"%>
    <%@include file="/views/_include/_html_link_shortcuticon.jsp"%>
    <c:if test="${jsDebug}">
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/global.css"/>
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/layer.css"/>
    </c:if>
    <c:if test="${!jsDebug}">
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/global.min.css?ver=${staticResourceVersion}"/>
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/style.min.css?ver=${staticResourceVersion}"/>
        <link rel="stylesheet" type="text/css" href="${staticContextPath}/assets/css/layer.min.css?ver=${staticResourceVersion}"/>
    </c:if>
</head>