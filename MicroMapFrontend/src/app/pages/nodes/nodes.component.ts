import { Component, OnInit } from '@angular/core';
import { Node } from '@angular/compiler';
import { CallsService } from 'app/calls.service';

@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'nodes.component.html'
})

export class NodesComponent implements OnInit{
   nodes: Node[]=[];
   showAlert=false;
   constructor(private callService:CallsService){

   }
    ngOnInit(){
        this.callService.getAllNodes().subscribe((res)=>{
            this.nodes=res;
        })
    }

    deleteNode(name: string){
        this.callService.deleteNode(name).subscribe(()=>{
            this.showAlert=true;
            this.callService.getAllNodes().subscribe((res)=>{
                this.nodes=res;
            })
        })
    }
}
