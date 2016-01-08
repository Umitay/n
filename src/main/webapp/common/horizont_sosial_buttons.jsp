<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p class="social-buttons" >Поделиться:
	<button class="btn btn-success social_share icon-gp" data-type="gp">G+</button>
    <button class="btn btn-success social_share icon-vk" data-type="vk">ВК</button>
    <button class="btn btn-success social_share icon-fb" data-type="fb">FB</button>
    <button class="btn btn-success social_share icon-tw" data-type="tw">TW</button>
    <button class="btn btn-success social_share icon-lj" data-type="lj">LJ</button>
    <button class="btn btn-success social_share icon-ok" data-type="ok">ОK</button>
    <button class="btn btn-success social_share icon-mr" data-type="mr">MR</button>
</p>
<style>
.social_share{
 	background-color: rgba(255, 255, 255, 0);
    color: #000;
    height: 25px;
    width: 40px;
 }
/*
@font-face {
    font-family: 'icomoon';
    src:    url('fonts/icomoon.eot?bvycj8');
    src:    url('fonts/icomoon.eot?bvycj8#iefix') format('embedded-opentype'),
        url('fonts/icomoon.ttf?bvycj8') format('truetype'),
        url('fonts/icomoon.woff?bvycj8') format('woff'),
        url('fonts/icomoon.svg?bvycj8#icomoon') format('svg');
    font-weight: normal;
    font-style: normal;
}
 .social_share{
 	background-color:rgba(255, 255, 255, 0);
    color: #ffffff;
    height: 30px;
    width: 30px;
 }
.social-buttons .icon-vk::before {
	background-color: #51749c !important;
    content: "\e603";
}
.social-buttons .icon-fb::before {
	background-color: #45639e !important;
    content: "\e619";
}
.social-buttons .icon-tw::before {
	background-color: #23b9eb !important;
    content: "\e604";
}
.social-buttons .icon-ok::before {
	background-color: #fa890f !important;
    content: "\e604";
}
.social-buttons .icon-lj::before {
	background-color: #fa890f !important;
    content: "\e604";
}
.social-buttons .icon-mr::before {
	background-color: #fa890f !important;
    content: "\e604";
}
.social-buttons .icon-gp::before {
	background-color: #FFF !important;
    content: "\e618";
}
*/
</style>