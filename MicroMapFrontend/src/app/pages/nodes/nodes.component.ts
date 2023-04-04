import { Component, OnInit } from '@angular/core';
import { Node } from '@angular/compiler';
import { CallsService } from 'app/calls.service';

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
    constructor(private callService: CallsService) {

    }
    ngOnInit() {
        this.callService.getAllNodes().subscribe((res) => {
            this.nodes = res;
        })
    }
    public deleteModel(name: string): void {
        document.getElementById('deleteModal').style.display = 'block';
        this.deleteName = name;
    }
    deleteNode() {
        document.getElementById('deleteModal').style.display = 'none';
        this.callService.deleteNode(this.deleteName).subscribe(() => {
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
            this.callService.getAllNodes().subscribe((res) => {
                this.nodes = res;
            })
        })
    }
   
}
