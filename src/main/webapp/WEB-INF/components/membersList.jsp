<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--                                list of members--%>
<c:set var="currentSection" value="${requestScope.get('section')}"/>
<c:set var="sections" value="${['all', 'teachers', 'students']}"/>
<c:set var="roles" value="${['admin', 'teacher', 'student']}"/>
<c:set var="isTeacher" value="${sessionScope.user.getRole() == 2}"/>
<c:set var="isAdmin" value="${sessionScope.user.getRole() == 1}"/>

<c:set var="hasMembers" value="${requestScope.members.size() > 0}"/>
<div x-data="{open: false}">


    <c:if test="${isAdmin}">
        <c:import url="/WEB-INF/components/membersForm.jsp"/>
    </c:if>

    <c:if test="${hasMembers}">
        <div class="relative pb-5 border-b border-gray-200 sm:pb-0">
            <div class="md:flex md:items-center md:justify-between">
                <h3 class="text-lg leading-6 font-medium text-gray-900 mb-4">Candidates</h3>
                <div class="mt-3 flex md:mt-0 md:absolute md:top-3 md:right-0">
                    <c:if test="${isAdmin}">
                        <button type="button"
                                @click="open = true"
                                class="ml-3 inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            Create Member
                        </button>
                    </c:if>
                </div>
            </div>
            <c:if test="${isAdmin}">
                <div class="mt-4">
                    <!-- Dropdown menu on small screens -->
                    <div class="sm:hidden">
                        <label for="current-tab" class="sr-only">Select a tab</label>
                        <select id="current-tab" name="current-tab"
                                class="block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md">
                            <c:forEach items="${sections}" var="tab">
                                <option
                                        value="${tab}">
                                        ${tab}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- Tabs at small breakpoint and up -->
                    <div class="hidden sm:block">
                        <nav class="-mb-px flex space-x-8">
                            <!-- Current: "border-indigo-500 text-indigo-600", Default: "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300" -->
                            <c:forEach items="${sections}" var="tab">
                                <c:set var="link"
                                       value="${tab == 'all' ? '/' : '/?section='}${tab == 'all' ? '' : tab}"/>
                                <a href="${link}"
                                   class="${currentSection == tab ? 'border-indigo-500 text-indigo-600 ' : 'border-transparent  text-gray-500 hover:text-gray-700 hover:border-gray-300'} capitalize whitespace-nowrap pb-4 px-1 border-b-2 font-medium text-sm ">${tab}</a>
                            </c:forEach>
                        </nav>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="flex flex-col">
            <div class="-my-2 sm:-mx-6 lg:-mx-8">
                <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                    <div class="  border border-gray-200">
                        <table class="min-w-full divide-y divide-gray-200">
                            <thead class="bg-gray-50">
                            <tr>
                                <th scope="col"
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    Name
                                </th>
                                <c:if test="${!isTeacher}">
                                    <th scope="col"
                                        class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                        Promotion
                                    </th>
                                </c:if>

                                <th scope="col"
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    Role
                                </th>

                            </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach items="${requestScope.members}" var="member">
                                <tr>
                                    <td class="px-2 py-4 whitespace-nowrap">
                                        <div class="flex items-center">
                                            <div class="ml-4">
                                                <div class="text-sm font-medium text-gray-900">${member.getName()}</div>
                                                <div class="text-sm text-gray-500">
                                                        ${member.getEmail()}
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <c:if test="${!isTeacher}">
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <c:import
                                                    url="/WEB-INF/components/promotionAssignList.jsp"/>
                                        </td>
                                    </c:if>

                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 capitalize">
                                            ${roles[member.getRole() - 1]}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <%--empty list of members--%>
    <c:if test="${!hasMembers}">
        <div class="text-center">
            <svg class="mx-auto h-12 w-12 text-gray-400" fill="none"
                 viewBox="0 0 24 24" stroke="currentColor"
                 aria-hidden="true">
                <path vector-effect="non-scaling-stroke"
                      stroke-linecap="round" stroke-linejoin="round"
                      stroke-width="2"
                      d="M9 13h6m-3-3v6m-9 1V7a2 2 0 012-2h6l2 2h6a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2z"/>
            </svg>
            <h3 class="mt-2 text-sm font-medium text-gray-900">No
                members</h3>
            <c:if test="${isAdmin}">
                <p class="mt-1 text-sm text-gray-500">Get started by creating a
                    new member.</p>
                <div class="mt-6">
                    <button type="button"
                            @click="open = true"
                            class="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-primaryColor hover:bg-primaryColor focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primaryCOlor">
                        <!-- Heroicon name: solid/plus -->
                        <svg class="-ml-1 mr-2 h-5 w-5"
                             xmlns="http://www.w3.org/2000/svg"
                             viewBox="0 0 20 20" fill="currentColor"
                             aria-hidden="true">
                            <path fill-rule="evenodd"
                                  d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z"
                                  clip-rule="evenodd"/>
                        </svg>
                        New Member
                    </button>
                </div>
            </c:if>
        </div>
    </c:if>
</div>


