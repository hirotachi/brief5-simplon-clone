<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--                                list of members--%>
<div class="bg-white  py-5 border-b border-gray-200 ">
    <div class="-ml-4 -mt-2 flex items-center justify-between flex-wrap sm:flex-nowrap">
        <div class="ml-4 mt-2">
            <h3 class="text-lg leading-6 font-medium text-gray-900">Members</h3>
        </div>
        <div class="ml-4 mt-2 flex-shrink-0" x-data="{open: true}"
             @closeForm="console.log('hello in here')">
            <button type="button"
                    @click="open = !open"
                    class="relative inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                New member
            </button>
            <c:import url="/WEB-INF/components/membersForm.jsp"/>
        </div>
    </div>
</div>
<div class="flex flex-col">
    <div class="-my-2  sm:-mx-6 lg:-mx-8">
        <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
            <div class=" overflow-hidden border border-gray-200">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                    <tr>
                        <th scope="col"
                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Name
                        </th>
                        <th scope="col"
                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Promotion
                        </th>

                        <th scope="col"
                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Role
                        </th>
                        <th scope="col" class="relative px-6 py-3">
                            <span class="sr-only">Edit</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="flex items-center">
                                <div class="flex-shrink-0 h-10 w-10">
                                    <img class="h-10 w-10 rounded-full"
                                         src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60"
                                         alt="">
                                </div>
                                <div class="ml-4">
                                    <div class="text-sm font-medium text-gray-900">Jane Cooper</div>
                                    <div class="text-sm text-gray-500">jane.cooper@example.com</div>
                                </div>
                            </div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-900">Ada Lovelace</div>
                            <div class="text-sm text-gray-500">Said Oudouane</div>
                        </td>

                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">Admin</td>
                        <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                            <a href="#" class="text-indigo-600 hover:text-indigo-900">Edit</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%--empty list of members--%>
<%--<div class="text-center">--%>
<%--    <svg class="mx-auto h-12 w-12 text-gray-400" fill="none"--%>
<%--         viewBox="0 0 24 24" stroke="currentColor"--%>
<%--         aria-hidden="true">--%>
<%--        <path vector-effect="non-scaling-stroke"--%>
<%--              stroke-linecap="round" stroke-linejoin="round"--%>
<%--              stroke-width="2"--%>
<%--              d="M9 13h6m-3-3v6m-9 1V7a2 2 0 012-2h6l2 2h6a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2z"/>--%>
<%--    </svg>--%>
<%--    <h3 class="mt-2 text-sm font-medium text-gray-900">No--%>
<%--        members</h3>--%>
<%--    <p class="mt-1 text-sm text-gray-500">Get started by creating a--%>
<%--        new member.</p>--%>
<%--    <div class="mt-6">--%>
<%--        <button type="button"--%>
<%--                class="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-primaryColor hover:bg-primaryColor focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primaryCOlor">--%>
<%--            <!-- Heroicon name: solid/plus -->--%>
<%--            <svg class="-ml-1 mr-2 h-5 w-5"--%>
<%--                 xmlns="http://www.w3.org/2000/svg"--%>
<%--                 viewBox="0 0 20 20" fill="currentColor"--%>
<%--                 aria-hidden="true">--%>
<%--                <path fill-rule="evenodd"--%>
<%--                      d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z"--%>
<%--                      clip-rule="evenodd"/>--%>
<%--            </svg>--%>
<%--            New Member--%>
<%--        </button>--%>
<%--    </div>--%>
<%--</div>--%>
<%----%>


