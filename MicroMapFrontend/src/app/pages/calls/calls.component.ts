import { Call } from 'app/call';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CallsService } from 'app/calls.service';
import { error } from 'console';
import { FileService } from 'app/file.service';
import { KeycloakService } from 'keycloak-angular';



@Component({
    selector: 'calls',
    moduleId: module.id,
    templateUrl: 'calls.component.html',
    styleUrls: ['./calls.component.css']
})

export class CallsComponent implements OnInit {
    calls: Call[] = [];
    deleteId: string = "";
    type: string = "sync";
    id: number;
    api: string;
    topic: string;
    eventProduced: string;
    description: string;
    showAlert = false;
    errorUpdateCall = false;
    showDeleteAlert = false;
    errorLoading =false;
    errorDelete = false;
    errorExport=false;
    isAdmin: boolean;

    constructor(private callsService: CallsService,private fileService: FileService ,private keycloakService: KeycloakService) { }
   
    ngOnInit() {
      const userRoles: string[] = this.keycloakService.getUserRoles();
      this.isAdmin = userRoles.includes('admin');
      

        this.callsService.getAllCalls().subscribe({
            next: (response: Call[]) => {
              this.calls = response;
            },
            error: (error) => {
              this.errorLoading = true;
            }
          });
        
        
        
    }
  
    public updateModel(id: number, type: string, api: string, topic: string, eventProduced: string, description: string): void {
        document.getElementById('updateModel').style.display = 'block';
        this.id = id;
        this.type = type;
        this.api = api;
        this.topic = topic;
        this.eventProduced = eventProduced;
        this.description = description;
    }
    updateCall(): void {
        const formData = new FormData();
        formData.append("type", this.type);
        if (this.type == 'sync') {
          formData.append("api", this.api);
        } else if (this.type == 'async') {
          formData.append("topic", this.topic);
          formData.append("eventProduced", this.eventProduced);
        }
        formData.append("description", this.description);
      
        this.callsService.updateCall(this.id, formData).subscribe({
          next: (res) => {
            document.getElementById('updateModel').style.display = 'none';
            if (res != null) {
              this.showAlert = true;
              setTimeout(() => {
                this.showAlert = false;
              }, 1000);
            } else {
              this.errorUpdateCall = true;
              setTimeout(() => {
                this.errorUpdateCall = false;
              }, 2000);
            }
      
            this.callsService.getAllCalls().subscribe({
              next: (response: Call[]) => {
                this.calls = response;
              },
              error: (error) => {
                this.errorLoading = true;
              }
            });
          },
          error: (error) => {
            this.errorUpdateCall = true;
            setTimeout(() => {
              this.errorUpdateCall = false;
            }, 2000);
          }
        });
      }
      
    public deleteModal(id: string): void {
        document.getElementById('id01').style.display = 'block';
        this.deleteId = id;
    }
    public deleteAllModal(): void {
        document.getElementById('deleteAllModal').style.display = 'block';
        
    }
    public deleteCall(): void {
        document.getElementById('id01').style.display = 'none';
        this.callsService.deleteCall(this.deleteId).subscribe({
          next: () => {
            this.showDeleteAlert = true;
            setTimeout(() => {
              this.showDeleteAlert = false;
            }, 1000);
      
            this.callsService.getAllCalls().subscribe({
              next: (response: Call[]) => {
                this.calls = response;
              },
              error: (error) => {
                this.errorLoading = true;
              }
            });
          },
          error: () => {
            this.errorDelete = true;
          }
        });
      }
      
      public deleteAll(): void {
        document.getElementById('deleteAllModal').style.display = 'none';
        this.callsService.deleteAll().subscribe({
          next: () => {
            this.showDeleteAlert = true;
            setTimeout(() => {
              this.showDeleteAlert = false;
            }, 1000);
      
            this.callsService.getAllCalls().subscribe({
              next: (response: Call[]) => {
                this.calls = response;
              },
              error: (error) => {
                this.errorLoading = true;
              }
            });
          },
          error: () => {
            this.errorDelete = true;
            setTimeout(() => {
              this.errorDelete = false;
            }, 1000);
          }
        });
      }
    exportCalls() {
        this.fileService.exportCalls().subscribe(
            {
                next: ((response) => {
                    this.saveFile(response);
                  }),
                error: (({})=>{
                    this.errorExport = true;
                    setTimeout(() => {
                        this.errorExport = false;
                    }, 1000);
                })
                

            }
        )
        
        
      }
    
      private saveFile(data: Blob) {
        const blob = new Blob([data], { type: 'application/octet-stream' });
        const url = window.URL.createObjectURL(blob);
        const saveas = require('file-saver');
        saveas.saveAs(blob, 'calls.xlsx');
      }
    
}
