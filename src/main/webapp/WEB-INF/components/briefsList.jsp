<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
  const removeBrief = (briefId) => {
    console.log("removeBrief " + briefId);
  }
</script>

<div x-data="{open: false , currentBrief: undefined}">
    <c:import url="/WEB-INF/components/briefsForm.jsp"/>
    <div>
        <label for="search" class="block text-sm font-medium text-gray-700">Briefs</label>
        <div class="mt-1 relative flex items-center">
            <input type="text" name="search" id="search"
                   style="padding: .5rem; border: 1px solid #e2e8f0; border-radius: 0.25rem; width: 100%;"
                   class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full pr-12 sm:text-sm border-gray-300 rounded-md">
            <div class="absolute inset-y-0 right-0 flex py-1.5 pr-1.5">
                <kbd class="inline-flex items-center  rounded px-2 text-sm font-sans font-medium text-gray-600">
                    <ion-icon name="search"></ion-icon>
                </kbd>
            </div>
        </div>
    </div>
    <div class="flow-root mt-6">
        <ul role="list" class="-my-5 divide-y divide-gray-200">
            <c:forEach items="${requestScope.briefs}" var="brief">
                <li class="py-4"
                    x-data='{brief: ${brief}}'>
                    <div class="flex items-center space-x-4">
                        <div class="flex-1 min-w-0">
                            <p class="text-sm font-medium text-gray-900 truncate">${brief.getName()}</p>
                        </div>
                        <div>
                            <button type="button"
                                    @click="removeBrief(${brief.getId() == null ? 0 : brief.getId()})"
                                    class="inline-flex items-center px-2.5 py-1.5 border border-transparent text-xs font-medium rounded text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                                <ion-icon name="trash-outline"></ion-icon>
                            </button>
                            <button type="button"
                                    @click="currentBrief = brief"
                                    class="inline-flex items-center px-2.5 py-1.5 border border-transparent text-xs font-medium rounded text-white bg-indigo-500 hover:bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                                <ion-icon name="create-outline"></ion-icon>
                            </button>
                        </div>
                    </div>
                </li>
            </c:forEach>


        </ul>
    </div>
    <div class="mt-6">
        <button
                @click="open = true"
                class="w-full flex justify-center items-center px-4 py-2 border border-gray-300 shadow-sm text-sm rounded-md font-medium text-white bg-indigo-600 hover:bg-indigo-700">
            Create
            Brief
        </button>
    </div>
</div>

<%--                                list of promotions --%>


<%--
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
        promotions</h3>
    <p class="mt-1 text-sm text-gray-500">Get started by creating a
        new promotions.</p>
    <div class="mt-6">
        <button type="button"
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
            New Promotion
        </button>
    </div>
</div>
--%>
