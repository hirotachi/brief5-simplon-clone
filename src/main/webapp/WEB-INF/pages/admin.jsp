<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: said
  Date: 11/12/2022
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simplon - Admin</title>
    <c:import url="/WEB-INF/components/scripts.jsp"/>
</head>
<body>
<div class="min-h-full  flex flex-col">
    <c:import url="/WEB-INF/components/header.jsp"/>
    <main class="-mt-24 pb-8 bg-white  grow pt-5 flex">
        <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:max-w-7xl lg:px-8 grow">
            <h1 class="sr-only">Page title</h1>
            <!-- Main 3 column grid -->
            <div class="grid grid-cols-1 gap-4 items-start lg:grid-cols-3 lg:gap-8">
                <!-- Left column -->
                <div class="grid grid-cols-1 gap-4 lg:col-span-2">
                    <section aria-labelledby="section-1-title">
                        <h2 class="sr-only" id="section-1-title">Section title</h2>
                        <div class=" bg-white">
                            <div>
                                <c:import url="/WEB-INF/components/membersList.jsp"/>
                            </div>
                        </div>
                    </section>
                </div>

                <!-- Right column -->
                <div class="grid grid-cols-1 gap-4">
                    <section aria-labelledby="section-2-title">
                        <h2 class="sr-only" id="section-2-title">Section title</h2>
                        <div class="rounded-lg bg-white overflow-hidden shadow">
                            <div class="p-6">
                                <c:import url="/WEB-INF/components/promotionsList.jsp"/>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 lg:max-w-7xl">
            <div class="border-t border-gray-200 py-8 text-sm text-gray-500 text-center sm:text-left">
                <span class="block sm:inline">&copy; 2021 Simplon Labs Inc.</span> <span
                    class="block sm:inline">All rights reserved.</span></div>
        </div>
    </footer>
</div>
</body>
</html>
