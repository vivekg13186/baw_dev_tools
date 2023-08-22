const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/connection/IndexPage.vue") },
      {
        path: "connection/:id",
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
        path: "testScript",
        component: () => import("pages/testscript/TestScriptPage.vue"),
      },
      {
        path: "testScript/:id",
        component: () => import("pages/testscript/EditTestScriptPage.vue"),
      },

      {
        path: "testData",
        component: () => import("pages/testdata/TestDataPage.vue"),
      },
      {
        path: "testData/:id",
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
