import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AdminLayoutRoutes } from './admin-layout.routing';


import { UserComponent }            from '../../pages/user/user.component';
import { TableComponent }           from '../../pages/table/table.component';



import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CallsComponent } from 'app/pages/calls/calls.component';
import { AddCallComponent } from 'app/pages/add-call/add-call.component';
import { NodesComponent } from 'app/pages/nodes/nodes.component';
import { AddNodeComponent } from 'app/pages/add-node/add-node.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule
  ],
  declarations: [
    CallsComponent,
    UserComponent,
    TableComponent,
    AddCallComponent,
    NodesComponent,
    AddNodeComponent
    
 
  ]
})

export class AdminLayoutModule {}
