import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NodeService } from 'app/node.service';

@Component({
    selector: 'user-cmp',
    moduleId: module.id,
    templateUrl: 'add-node.component.html'
})

export class AddNodeComponent implements OnInit {
    name: string;
    type: string;
    showAlert = false;
    errorAddNode = false;
    constructor(private nodeService: NodeService) {

    }
    ngOnInit() {
    }
    
    addNode(form: NgForm): void {
        const formData = new FormData();
        formData.append("name", this.name);
        formData.append("type", this.type);
        this.nodeService.addNode(formData).subscribe((res) => {
            if (res != null) {
                this.showAlert = true;
                setTimeout(() => {
                    this.showAlert = false;
                }, 1000);
                form.reset();
            }
            else {
                this.errorAddNode = true;
                setTimeout(() => {
                    this.errorAddNode = false;
                }, 2000);
                form.reset();
            }
        })

    }
   
}