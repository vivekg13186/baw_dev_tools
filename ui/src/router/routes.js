const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/connection/IndexPage.vue") },
      {
        path: "editConnnection/:id",
        component: () => import("pages/connection/ConnectionEditorPage.vue"),
      },
      {
        path: "viewApp/:id",
        component: () => import("pages/connection/ViewAppsPage.vue"),
      },
      {
        path: "viewSnapshot",
        name: "viewSnapshot",
        component: () => import("pages/connection/SnapshotInfo.vue"),
      },
      {
        path: "instance",
        component: () => import("pages/instancedtl/InstanceDetails.vue"),
      },
      {
        path: "testSummary",
        component: () => import("pages/testcase/TestSummary.vue"),
      },
      {
        path: "testCaseEditor/:id",
        component: () => import("pages/testcase/TestCaseEditor.vue"),
      },
      {
        path: "testData",
        component: () => import("pages/testdata/TestDataPage.vue"),
      },
      {
        path: "testData/:name",
        component: () => import("pages/testdata/EditTestDataPage.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
