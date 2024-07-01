<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style>
		body {
			margin: 0;
			padding: 0;
		}
		
		.header {
			height: 100px;
			line-height: 100px;
			background-color: #ffa07a;
		}
		
		.menu {
			display: flex;
		}
		
		.menu-item {
			list-style-type: none;
		}
		
		.menu-item.rank1 {
			flex-grow: 1;
			flex-basis: 10%;
		}
				
		.menu-item.rank2 {
			flex: 1;
			flex-basis: 80%;
			text-align: right;
			margin-right: 30px;
		}
		
		.menu-link {
			text-align: center;
			color: white;
			font-size: 1.2rem;
			font-weight: bold;
		}
		
		.menu-link:hover {
			color: #2828cd;
		}
		
		.menu > .menu-item > a {
			text-decoration: none;
		}
	</style>
</head>
<body>
	<header>
		<nav class="header">
			<ul class="menu">
				<li class="menu-item rank1">
					<a href="javascript:void(0);" class="menu-link">HOME</a>
				</li>
				<li class="menu-item rank1">
					<a href="javascript:void(0);" class="menu-link">BOARD</a>
				</li>
				<li class="menu-item rank2">
					<a href="javascript:void(0);" class="menu-link">LOGIN</a>
				</li>
			</ul>
		</nav>
	</header>
</body>
</html>