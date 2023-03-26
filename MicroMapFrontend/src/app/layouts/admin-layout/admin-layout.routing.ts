import { Routes } from '@angular/router';



import { CallsComponent } from 'app/pages/calls/calls.component';
import { AddCallComponent } from 'app/pages/add-call/add-call.component';
import { NodesComponent } from 'app/pages/nodes/nodes.component';
import { AddNodeComponent } from 'app/pages/add-node/add-node.component';

export const AdminLayoutRoutes: Routes = [
   
   
    {path:'calls',component:CallsComponent},
    {path:'addCall',component:AddCallComponent},
    {path:'nodes',component:NodesComponent},
    {path:'addNode',component:AddNodeComponent},

  
];
