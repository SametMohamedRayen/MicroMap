import { Component, OnInit } from '@angular/core';


export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
  
    { path: '/user',          title: 'User Profile',      icon:'nc-single-02',  class: '' },
    { path: '/table',         title: 'Table List',        icon:'nc-tile-56',    class: '' },
    { path: '/nodes',         title: 'Nodes',        icon:'nc-tile-56',    class: '' },
    { path: '/addNode',         title: 'Add Node',        icon:'nc-simple-add',    class: '' },
    { path: '/calls',         title: 'Calls',        icon:'nc-tile-56',    class: '' },
    
    { path: '/addCall',         title: 'Add Call',        icon:'nc-simple-add',    class: '' },
    
   
];

@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
}
