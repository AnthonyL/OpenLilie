
<%@ page import="org.data.ModuleENT" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleENT.label', default: 'ModuleENT')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-moduleENT" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-moduleENT" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list moduleENT">
			
				<g:if test="${moduleENTInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="moduleENT.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${moduleENTInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleENTInstance?.createur}">
				<li class="fieldcontain">
					<span id="createur-label" class="property-label"><g:message code="moduleENT.createur.label" default="Createur" /></span>
					
						<span class="property-value" aria-labelledby="createur-label"><g:link controller="user" action="show" id="${moduleENTInstance?.createur?.id}">${moduleENTInstance?.createur?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleENTInstance?.projectOwner}">
				<li class="fieldcontain">
					<span id="projectOwner-label" class="property-label"><g:message code="moduleENT.projectOwner.label" default="Project Owner" /></span>
					
						<span class="property-value" aria-labelledby="projectOwner-label"><g:link controller="user" action="show" id="${moduleENTInstance?.projectOwner?.id}">${moduleENTInstance?.projectOwner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${moduleENTInstance?.id}" />
					<g:link class="edit" action="edit" id="${moduleENTInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
