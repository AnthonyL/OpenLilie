<%@ page import="org.data.ModuleENT" %>



<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="moduleENT.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="30" required="" value="${moduleENTInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'createur', 'error')} ">
	<label for="createur">
		<g:message code="moduleENT.createur.label" default="Createur" />
		
	</label>
	<g:select id="createur" name="createur.id" from="${org.data.User.list()}" optionKey="id" value="${moduleENTInstance?.createur?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'projectOwner', 'error')} ">
	<label for="projectOwner">
		<g:message code="moduleENT.projectOwner.label" default="Project Owner" />
		
	</label>
	<g:select id="projectOwner" name="projectOwner.id" from="${org.data.User.list()}" optionKey="id" value="${moduleENTInstance?.projectOwner?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

