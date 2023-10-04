
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        name: 'home',
        component: () => import('pages/HomePage.vue')
      },
      {
        path: 'about',
        name: 'about',
        component: () => import('pages/AboutPage.vue')
      },
      {
        path: 'login',
        component: () => import('pages/LoginPage.vue')
      },
      {
        path: 'register',
        component: () => import('pages/RegisterPage.vue')
      },
      {
        path: 'profile',
        component: () => import('pages/EditProfilePage.vue')
      },
      {
        path: 'panels',
        component: () => import('pages/PanelsPage.vue')
      },
      {
        path: 'panels/add',
        name: 'panels-add',
        component: () => import('pages/EditPanelInstallationPage.vue')
      },
      {
        path: 'panels/:panelInstallationId/edit',
        component: () => import('pages/EditPanelInstallationPage.vue'),
        props: true
      },
      {
        path: 'marketplace',
        name: 'marketplace',
        component: () => import('pages/MarketplacePage.vue')
      },
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
