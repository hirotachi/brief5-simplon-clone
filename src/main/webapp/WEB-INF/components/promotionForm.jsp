<script defer>
  const createPromotion = (data, close) => {
    console.log("createPromotion", data);
    close(false);
  }
  const initialPromotionFormState = {
    name: "",
    year: new Date().getFullYear(),
  };

</script>

<template x-if="open">
    <div class="relative z-10" aria-labelledby="modal-title" role="dialog" aria-modal="true"
         x-data="{form: initialPromotionFormState}"
         @click="open = false">
        <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"></div>

        <div class="fixed inset-0 z-10 overflow-y-auto">
            <div class="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                <div @click.stop=""
                     class="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg">
                    <div class="bg-white px-0 pt-5 pb-4 sm:p-6 sm:pb-4">
                        <div class="">

                            <div class="">
                                <h3 class="text-lg font-medium leading-6 text-gray-900"
                                    id="modal-title">Create Promotion</h3>
                                <div class="mt-2">

                                    <form action="#" method="POST"
                                          @submit.prevent="createPromotion(form, (newOpen) => {open = newOpen})">
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

                                                    <div class="col-span-6 sm:col-span-4">
                                                        <label for="year"
                                                               class="block text-sm font-medium text-gray-700">Year</label>
                                                        <input x-model.number="form.year"
                                                               type="number"
                                                               name="year"
                                                               id="year"
                                                               autocomplete="year"
                                                               class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
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
                        <button type="button"
                                @click="createPromotion(form, (newOpen) => {open = newOpen})"
                                class="inline-flex w-full justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm">
                            Create
                        </button>
                        <button type="button" @click="open = false"
                                class="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
