import { Component, OnInit } from '@angular/core';
import { Node } from 'app/node';
import { CallsService } from 'app/calls.service';
import { NodeService } from 'app/node.service';
import { error } from 'console';

@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'nodes.component.html',
    styleUrls: ['./nodes.component.css']
})

export class NodesComponent implements OnInit {
    deleteName: string;
    nodes: Node[] = [];
    showAlert = false;
    errorLoading=false;
    errorDelete=false;


    constructor(private nodeService: NodeService) {

    }
    ngOnInit() {
        this.nodeService.getAllNodes().subscribe((res) => {
            this.nodes = res;
        }
        ,
        (error)=>{
            this.errorLoading=true;
        })
    }
    public deleteModel(name: string): void {
        document.getElementById('deleteModal').style.display = 'block';
        this.deleteName = name;
    }
    deleteNode() {
        document.getElementById('deleteModal').style.display = 'none';
        this.nodeService.deleteNode(this.deleteName).subscribe(() => {
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
            this.nodeService.getAllNodes().subscribe((res) => {
                this.nodes = res;
            },(error)=>{
                this.errorLoading=true;
            })
        },
        (error)=>{
            this.errorDelete = true;
            setTimeout(() => {
                this.errorDelete = false;
            }, 1000);
        })
    }
   
}
