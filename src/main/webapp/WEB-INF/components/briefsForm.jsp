<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script defer>
  const createBrief = (data, close) => {
    console.log("createBrief", data);
    close(false);
  }
  const initialBriefState = {
    name: "",
    year: new Date().getFullYear(),
  };

  const updateBrief = (brief, updates, close) => {
    console.log("updateBrief", brief, updates);
    close(false);
  }

</script>

<template x-if="open || currentBrief">
    <div class="relative z-10" aria-labelledby="modal-title" role="dialog" aria-modal="true"
         x-data="{form: {...initialBriefState, ...(currentBrief ?? {})}, close: () => {
         open = false;
            currentBrief = null;
         },
updateOpen: (newOpen) => {
open = newOpen;
}}
"
         @click="close()">
        <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"></div>

        <div class="fixed inset-0 z-10 overflow-y-auto">
            <div class="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                <div @click.stop=""
                     class="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg">
                    <div class="bg-white px-0 pt-5 pb-4 sm:p-6 sm:pb-4">
                        <div class="">
                            <div class="">
                                <h3 x-text="currentBrief? 'Update Brief': 'Create Brief'"
                                    class="text-lg font-medium leading-6 text-gray-900"
                                    id="modal-title"></h3>
                                <div class="mt-2">

                                    <form x-ref="form" action="/briefs" method="POST">
                                        <div class="overflow-hidden min-w-full">
                                            <div class=" px-1 py-5 sm:p-6">
                                                <div class="flex gap-6 flex-col">
                                                    <div>
                                                        <label for="name"
                                                               class="block text-sm font-medium text-gray-700">Name</label>
                                                        <input x-model="form.name" type="text"
                                                               name="name"
                                                               id="name"
                                                               autocomplete="given-name"
                                                               class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                                                    </div>

                                                    <div>
                                                        <label for="description"
                                                               class="block text-sm font-medium text-gray-700">Description</label>
                                                        <textarea x-model="form.description"
                                                                  name="description"
                                                                  id="description"
                                                                  autocomplete="given-name"
                                                                  class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                                                        </textarea>
                                                    </div>


                                                </div>
                                            </div>

                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                        <button x-text="currentBrief ? 'Update': 'Create'" type="button"
                                @click="$refs.form.submit()"
                                class="inline-flex w-full justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm">
                        </button>
                        <button type="button" @click="close()"
                                class="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
