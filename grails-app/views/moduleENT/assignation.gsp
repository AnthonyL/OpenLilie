<%@ page import="org.data.ModuleENT" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleENT.label', default: 'ModuleENT')}" />
		<g:set var="moduleENTId" value="${moduleENTInstance.id}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-moduleENT" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="show" action="show" id="${moduleENTInstance.id}" >ModuleENT</g:link></li>
			</ul>
		</div>
		<div id="create-moduleENT" class="content scaffold-create" role="main">
			<h1>Assigner un project owner à ${moduleENTInstance.title }</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${moduleENTInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${moduleENTInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="affectation" params="[moduleENTId: moduleENTId]">
				<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'projectOwner', 'error')} ">
					<label for="projectOwner">
						<g:message code="moduleENT.projectOwner.label" default="Project Owner" />
					</label>
					<g:select id="projectOwner" name="projectOwner.id" from="${listProjectOwner}" optionKey="id" value="${moduleENTInstance?.projectOwner?.id}" class="many-to-one" noSelection="['null': '']"/>
				</div>
				<fieldset class="buttons">
					<g:submitButton name="affecter" class="save" value="Affecter" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
