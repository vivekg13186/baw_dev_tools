const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      {
        path: "editConnnection/:id",
        component: () => import("pages/ConnectionEditorPage.vue"),
      },
      {
        path: "viewApp/:id",
        component: () => import("pages/ViewAppsPage.vue"),
      },
      {
        path: "viewSnapshot",
        name: "viewSnapshot",
        component: () => import("pages/SnapshotInfo.vue"),
      },
      {
        path: "instance",
        component: () => import("pages/InstanceDetails.vue"),
      },
      {
        path: "testSummary",
        component: () => import("pages/TestSummary.vue"),
      },
      {
        path: "testScript",
        component: () => import("pages/TestScript.vue"),
      },
      {
        path: "testData",
        component: () => import("pages/TestDataPage.vue"),
      },
      {
        path: "testData/:name",
        component: () => import("pages/EditTestDataPage.vue"),
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
