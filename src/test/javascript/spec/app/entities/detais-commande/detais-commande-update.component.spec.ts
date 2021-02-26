import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisCommandeUpdateComponent } from 'app/entities/detais-commande/detais-commande-update.component';
import { DetaisCommandeService } from 'app/entities/detais-commande/detais-commande.service';
import { DetaisCommande } from 'app/shared/model/detais-commande.model';

describe('Component Tests', () => {
  describe('DetaisCommande Management Update Component', () => {
    let comp: DetaisCommandeUpdateComponent;
    let fixture: ComponentFixture<DetaisCommandeUpdateComponent>;
    let service: DetaisCommandeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisCommandeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetaisCommandeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetaisCommandeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetaisCommandeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisCommande(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisCommande();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
