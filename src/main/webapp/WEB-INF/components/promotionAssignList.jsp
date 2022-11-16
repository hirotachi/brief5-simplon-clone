<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  const assignStudentToPromo = (memberId, promotionId) => {
    const url = `<c:url value="/users/${memberId}?promotionId=${promotionId}"/>`;
    console.log(url);
  }
</script>
<div x-data="{open: false, search: ''}" @mousedown.outside="open = false">
    <div class="relative mt-1">
        <input x-model="search" id="combobox" type="text"
               class="w-full rounded-md border border-gray-300 bg-white py-2 pl-3 pr-12 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm"
               role="combobox" aria-controls="options" aria-expanded="false">
        <button type="button"
                @click="open = !open"
                class="absolute inset-y-0 right-0 flex items-center rounded-r-md px-2 focus:outline-none">
            <!-- Heroicon name: solid/selector -->
            <svg class="h-5 w-5 text-gray-400" xmlns="http://www.w3.org/2000/svg"
                 viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                <path fill-rule="evenodd"
                      d="M10 3a1 1 0 01.707.293l3 3a1 1 0 01-1.414 1.414L10 5.414 7.707 7.707a1 1 0 01-1.414-1.414l3-3A1 1 0 0110 3zm-3.707 9.293a1 1 0 011.414 0L10 14.586l2.293-2.293a1 1 0 011.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z"
                      clip-rule="evenodd"/>
            </svg>
        </button>

        <template x-if="open">
            <ul class="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm"
                id="options" role="listbox">
                <c:forEach items="${requestScope.promotions}" var="promotion">
                    <li @click="window.location.href = '/users/'+ memberId + '?promotionId=${promotion.id}'"
                        x-data="{hovered: false,assigned: promotions.some( promo => promo === ${promotion.getId()})}"
                        x-show="search === '' || '${promotion.getName()}'.toLowerCase().includes(search.toLowerCase())"
                        @mouseenter="hovered = true"
                        @mouseleave="hovered = false"
                        class="relative cursor-default select-none py-2 pl-3 pr-9 cursor-pointer"
                        :class="{ 'text-white bg-indigo-600': hovered, 'text-gray-900': !hovered }"
                        id="option-0" role="option" tabindex="-1">
                        <div class="flex">
                            <!-- Selected: "font-semibold" -->
                            <span class="truncate">${promotion.getName()}</span>
                            <!-- Active: "text-indigo-200", Not Active: "text-gray-500" -->
                            <c:if test="${promotion.getTeacher() != null}">

                            <span :class="{'text-white-20': hovered, 'text-gray-500': !hovered}"
                                  class="ml-2 truncate">@${promotion.getTeacher().getName()}</span>
                            </c:if>

                        </div>

                        <template
                                x-if="assigned">
                            <span
                                    class="absolute inset-y-0 right-0 flex items-center pr-4 "
                                    :class="{'text-white': hovered, 'text-indigo-600': !hovered}">
          <!-- Heroicon name: solid/check -->
          <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
               fill="currentColor" aria-hidden="true">
            <path fill-rule="evenodd"
                  d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                  clip-rule="evenodd"/>
          </svg>
        </span>
                        </template>
                    </li>
                </c:forEach>
            </ul>
        </template>
    </div>
</div>