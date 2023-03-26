import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CallsService } from 'app/calls.service';

@Component({
    selector: 'user-cmp',
    moduleId: module.id,
    templateUrl: 'add-node.component.html'
})

export class AddNodeComponent implements OnInit {
    constructor(private callService: CallsService) {

    }
    name: string;
    type: string;
    showAlert = false;
    addNode(form: NgForm): void {
        const formData = new FormData();
        formData.append("name", this.name);
        formData.append("type", this.type);
        this.callService.addNode(formData).subscribe(() => {
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
            form.reset();
        })

    }
    ngOnInit() {
    }
}