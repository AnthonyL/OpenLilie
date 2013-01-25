<%@ page import="openlilie.DomainTest" %>



<div class="fieldcontain ${hasErrors(bean: domainTestInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="domainTest.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${domainTestInstance?.name}"/>
</div>

