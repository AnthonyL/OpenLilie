<%@ page import="org.data.ModuleENT" %>



<div class="fieldcontain ${hasErrors(bean: moduleENTInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="moduleENT.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="30" required="" value="${moduleENTInstance?.title}"/>
</div>

