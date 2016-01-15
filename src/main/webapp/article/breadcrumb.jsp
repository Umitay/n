<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="breadcrumb">
<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb">
  <a href="${domain_url}" itemprop="url">
    <span itemprop="title" class="glyphicon glyphicon-home" >${site_name}</span>
  </a> ›
</div>  
<div  itemprop="child" itemscope itemtype="http://data-vocabulary.org/Breadcrumb">
  <a href="${domain_url}article/list" itemprop="url" class="capitalize" >
    <span itemprop="title">Интересно и Полезно</span>
  </a> ›
</div>
<div  itemprop="child" itemscope itemtype="http://data-vocabulary.org/Breadcrumb">
    <meta itemprop="url" content="${share_url}">
    <span itemprop="title">${article.name}</span>
</div>
</div>