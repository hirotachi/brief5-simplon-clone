<script defer>
  const createMember = (data, close) => {
    const formData = new FormData();
    Object.keys(data).forEach(key => formData.append(key, data[key]));
    fetch("/users", {
      method: "POST",
      body: formData
    })
    .then(response => response.json())
    .then(data => {
      if (data.error) {
        alert(data.error);
      } else {
        close();
        window.location.reload();
      }
    });
  }
  const initialFormState = {
    name: "",
    email: "",
    role: 3,
  };

</script>

<template x-if="open">
    <div class="relative z-10" aria-labelledby="modal-title" role="dialog" aria-modal="true"
         x-data="{form: initialFormState}"
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
                                    id="modal-title">Create Member</h3>
                                <div class="mt-2">

                                    <form x-ref="form" action="/users" method="POST">
                                        <div class="overflow-hidden min-w-full">
                                            <div class=" px-1 py-5 sm:p-6">
                                                <div class="flex gap-6 flex-col">
                                                    <div>
                                                        <label for="name"
                                                               class="block text-sm font-medium text-gray-700">
                                                            Full name</label>
                                                        <input x-model="form.name" type="text"
                                                               name="name"
                                                               id="name"
                                                               autocomplete="given-name"
                                                               class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                                                    </div>

                                                    <div class="col-span-6 sm:col-span-4">
                                                        <label for="email"
                                                               class="block text-sm font-medium text-gray-700">Email
                                                            address</label>
                                                        <input x-model="form.email" type="text"
                                                               name="email"
                                                               id="email"
                                                               autocomplete="email"
                                                               class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                                                    </div>

                                                    <div class="col-span-6 sm:col-span-4">
                                                        <label for="password"
                                                               class="block text-sm font-medium text-gray-700">Password</label>
                                                        <input x-model="form.password"
                                                               type="password"
                                                               name="password"
                                                               id="password"
                                                               class="mt-1 w-100 block w-full p-2 rounded-md bg-gray-50 shadow  focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                                                    </div>

                                                    <div class="col-span-6 sm:col-span-3">
                                                        <label for="role"
                                                               class="block text-sm font-medium text-gray-700">Role</label>
                                                        <select x-model.number="form.role"
                                                                id="role"
                                                                name="role"
                                                                autocomplete="country-name"
                                                                class="mt-1 block w-full rounded-md border border-gray-300 bg-white py-2 px-3 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm">
                                                            <option value="1">Admin</option>
                                                            <option value="2">Teacher</option>
                                                            <option value="3">Student</option>
                                                        </select>
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
                                @click="$refs.form.submit()"
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
