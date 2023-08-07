const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      {
        path: "editConnnection/:id",
        component: () => import("pages/ConnectionEditor.vue"),
      },
      {
        path: "viewApp/:id",
        component: () => import("pages/ViewApps.vue"),
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
