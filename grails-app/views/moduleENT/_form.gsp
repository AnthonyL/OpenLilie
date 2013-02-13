<%@ page import="org.data.ModuleENT" %>



<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'title2', 'error')}">
	<label for="title2">
		<g:message code="moduleENT.title2.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title2" maxlength="30" required="" value="${moduleENTInstance?.title2}"/>
</div>

