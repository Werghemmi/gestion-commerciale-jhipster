import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { ReglementAchatUpdateComponent } from 'app/entities/reglement-achat/reglement-achat-update.component';
import { ReglementAchatService } from 'app/entities/reglement-achat/reglement-achat.service';
import { ReglementAchat } from 'app/shared/model/reglement-achat.model';

describe('Component Tests', () => {
  describe('ReglementAchat Management Update Component', () => {
    let comp: ReglementAchatUpdateComponent;
    let fixture: ComponentFixture<ReglementAchatUpdateComponent>;
    let service: ReglementAchatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementAchatUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReglementAchatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReglementAchatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReglementAchatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReglementAchat(123);
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
        const entity = new ReglementAchat();
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
