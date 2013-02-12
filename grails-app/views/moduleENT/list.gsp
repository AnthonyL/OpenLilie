
<%@ page import="org.data.ModuleENT" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleENT.label', default: 'ModuleENT')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-moduleENT" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<sec:ifAllGranted roles="ROLE_GOVERNOR">
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifAllGranted>
				<sec:ifNotLoggedIn>
					<li><g:link class="login" controller="login" action="connection">Login</g:link></li>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<li><span>${connectFullName} </span><g:link class="logout" controller="logout" action="index">Logout</g:link></li>
				</sec:ifLoggedIn>
			</ul>
		</div>
		<div id="list-moduleENT" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'moduleENT.title.label', default: 'Title')}" />
					
						<th><g:message code="moduleENT.createur.label" default="Createur" /></th>
					
						<th><g:message code="moduleENT.projectOwner.label" default="Project Owner" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${moduleENTInstanceList}" status="i" var="moduleENTInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${moduleENTInstance.id}">${fieldValue(bean: moduleENTInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: moduleENTInstance, field: "createur")}</td>
					
						<td>${fieldValue(bean: moduleENTInstance, field: "projectOwner")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${moduleENTInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
